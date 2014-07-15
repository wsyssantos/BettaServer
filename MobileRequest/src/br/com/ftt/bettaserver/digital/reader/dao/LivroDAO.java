package br.com.ftt.bettaserver.digital.reader.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import br.com.ftt.bettaserver.digital.reader.vo.Livro;
import br.com.ftt.bettaserver.restful.dao.ParametrosDAO;
import br.com.ftt.bettaserver.restful.database.util.DataBaseUtil;
import br.com.ftt.bettaserver.restful.form.ChaveParametro;

public class LivroDAO
{
    public List<Livro> buscaTodosLivros( )
    {
        List<Livro> livros = null ;
        Connection con = null ;
        PreparedStatement pstmt = null ;
        ResultSet rs = null ;
        String sql = "select id, titulo, autor, editora, numero_paginas, endereco_arquivo from livros order by titulo" ;
        ParametrosDAO dao = new ParametrosDAO( ) ;
        try
        {
            String urlStreaming = dao.buscaValorDeParametro( ChaveParametro.STREAMING_PATH ) ;
            
            con = DataBaseUtil.getConnection( );
            pstmt = con.prepareStatement( sql );
            rs = pstmt.executeQuery( ) ;
            
            if( rs.next( ) )
            {
                livros = new LinkedList<Livro>( ) ;
                do
                {
                    Livro livro = new Livro( ) ;
                    livro.setId( rs.getInt( "id" ) ) ;
                    livro.setTitulo( rs.getString( "titulo" ) ) ;
                    livro.setAutor( rs.getString( "autor" ) ) ;
                    livro.setEditora( rs.getString( "editora" ) ) ;
                    livro.setNumeroPaginas( rs.getInt( "numero_paginas" ) ) ;
                    String urlLivro = urlStreaming + rs.getString( "endereco_arquivo" ) ;
                    livro.setEnderecoArquivo( urlLivro ) ;
                    livros.add( livro ) ;
                }
                while( rs.next( ) ) ;
            }
        }
        catch( SQLException e )
        {
            e.printStackTrace( ) ;
        }
        finally
        {
            DataBaseUtil.close( con, pstmt, rs ) ;
        }
        
        return livros ;
    }
}
