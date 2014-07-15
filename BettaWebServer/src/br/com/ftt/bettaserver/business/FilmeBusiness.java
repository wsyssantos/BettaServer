package br.com.ftt.bettaserver.business;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import br.com.ftt.bettaserver.dao.FilmesDAO;
import br.com.ftt.bettaserver.dao.ParametrosDAO;
import br.com.ftt.bettaserver.form.ChaveParametro;
import br.com.ftt.bettaserver.form.Filme;
import br.com.ftt.bettaserver.form.Playlist;
import br.com.ftt.videosplitter.VideoCoder;
import br.com.ftt.videosplitter.util.StringConstants;

public class FilmeBusiness
{
    private static FilmeBusiness business;

    private FilmeBusiness( )
    {
    	
    }
    
    public List<Filme> pesquisaFilmes( String nome )
    {
        FilmesDAO     filmesDAO = new FilmesDAO( ) ;
        return filmesDAO.pesquisaFilmes( nome ) ;
    }

    public boolean excluirFilme( Filme filme )
    {
        ParametrosDAO paramDao = new ParametrosDAO( );
        FilmesDAO     filmesDAO = new FilmesDAO( ) ;
        String pathVideos = paramDao.buscaValorDeParametro( ChaveParametro.MEDIA_ADDESS );
        String pathFilme = pathVideos + StringConstants.pathSeparator + filme.getNome( ) ;
        
        File arquivoFilme = new File( pathFilme ) ;
        try
        {
            deleteDirectory( arquivoFilme ) ;
        }
        catch( Exception e )
        {
            e.printStackTrace( ) ;
        }
        return filmesDAO.excluirFilme( filme.getId( ) ) ;        
    }

    private boolean deleteDirectory( File path )
    {
        if ( path.exists( ) )
        {
            File[ ] files = path.listFiles( );
            for ( int i = 0 ; i < files.length ; i++ )
            {
                if ( files[ i ].isDirectory( ) )
                {
                    deleteDirectory( files[ i ] );
                }
                else
                {
                    files[ i ].delete( );
                }
            }
        }
        return ( path.delete( ) );
    }

    public List<Filme> carregaTodosFilmes( )
    {
        FilmesDAO dao = new FilmesDAO( );
        return dao.carregaTodosFilmes( );
    }

    public boolean inserirNovoFilme( Filme filme )
    {
        boolean retorno = true;
        ParametrosDAO paramDao = new ParametrosDAO( );
        FilmesDAO filmesDao = new FilmesDAO( );
        String pathVideos = paramDao.buscaValorDeParametro( ChaveParametro.MEDIA_ADDESS );

        try
        {
            filme.setUrlImagemFileName( copyImageFile( filme.getFileImagem( ),
                    filme.getNome( ), pathVideos ) );
            
            int filmeId = filmesDao.incuirFilme( filme );
            long duracao = 0;
            
            String url = filme.getFileFilme( ).getAbsolutePath( ) ;
            VideoCoder videoCoder = new VideoCoder( url,
                    filme.getQualidades( ), pathVideos,
                    filme.getNome( ) );
            Map<Integer, String> mapPlaylists = videoCoder.encodeVideos( );

            List<Playlist> playlists = new LinkedList<Playlist>( );
            
             duracao = videoCoder.getDuracao( ) ;

            if ( filmeId != 0 )
            {
                if ( mapPlaylists != null && !mapPlaylists.isEmpty( ) )
                {
                    for ( Map.Entry<Integer, String> entry : mapPlaylists
                            .entrySet( ) )
                    {
                        int bitrate = entry.getKey( );
                        String playlist = entry.getValue( );

                        Playlist pList = new Playlist( );
                        pList.setIdFilme( filmeId );
                        pList.setBitrate( bitrate );
                        pList.setIdIdiomaFilme( 1 );
                        pList.setPlaylistName( playlist );

                        playlists.add( pList );
                    }

                    retorno = retorno
                            & filmesDao
                                    .cadastrarPlaylistsDeFilmes( playlists );
                }
            }
            else
            {
                retorno = false;
            }
            
            filmesDao.atualizarDuracao( filmeId, duracao ) ;
        }
        catch ( Throwable e )
        {
            e.printStackTrace( );
            retorno = false;
        }
        return retorno;
    }
    

    private String copyImageFile( File imagemFile, String pathMovie,
            String pathMidia ) throws IOException
    {
        String newImagePath = "";

        newImagePath = pathMidia + StringConstants.pathSeparator + pathMovie.replaceAll( " ", "_" ) + StringConstants.pathSeparator
                + imagemFile.getName( ).replaceAll( " ", "_" );

        String imagename = StringConstants.pathSeparator + pathMovie + StringConstants.pathSeparator + imagemFile.getName( );

        imagename.replaceAll( " ", "_" ) ;
        
        File newImage = new File( newImagePath );

        if ( !newImage.exists( ) )
        {
            File paths = newImage.getParentFile( );
            if(paths.mkdirs( ))
            {
                newImage.createNewFile( );
            }
        }

        FileUtils.copyFile( imagemFile, newImage );

        return imagename.replace( '\\', '/' );
    }

    public static FilmeBusiness getInstance( )
    {
        if ( business == null )
        {
            business = new FilmeBusiness( );
        }
        return business;
    }
}
