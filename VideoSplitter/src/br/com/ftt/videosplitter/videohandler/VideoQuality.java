package br.com.ftt.videosplitter.videohandler;

public enum VideoQuality
{
    ULTRA_LOW, 
    EXTRA_LOW, 
    LOW, 
    LOW_MEDIUM, 
    HIGH_MEDIUM, 
    HIGH, 
    EXTRA_HIGH, 
    ULTRA_HIGH ;
    
    public int getValue(  ) 
    {
        switch(this)
        {
            case ULTRA_LOW : 
                return 80000;
            case EXTRA_LOW:
                return 110000;
            case LOW:
                return 330000;
            case LOW_MEDIUM:
                return 688301;
            case HIGH_MEDIUM:
                return 1308077;
            case HIGH:
                return 1927853;
            case EXTRA_HIGH:
                return 2650941;
            case ULTRA_HIGH:
                return 3477293;
            default: 
                return 0;            
        }
    }
    
    @Override
    public String toString( )
    {
        switch(this)
        {
            case ULTRA_LOW : 
                return "ultralow";
            case EXTRA_LOW:
                return "extralow";
            case LOW:
                return "low";
            case LOW_MEDIUM:
                return "lowmedium";
            case HIGH_MEDIUM:
                return "highmedium";
            case HIGH:
                return "high";
            case EXTRA_HIGH:
                return "extrahigh";
            case ULTRA_HIGH:
                return "ultrahigh";
            default: 
                return "";            
        }
    }
}
