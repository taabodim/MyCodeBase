package com.admarketplace.unsafe;
public enum MetaAlgorithm
{
    WaitForAllResults(0),
    ReturnFirstOne(1),
    WaitReduction(2),
    MinWaitTime(3);
    
    MetaAlgorithm(int value)
    {
        this.value = value;        
    }
    
    private final int value;
    
    public int value()
    { 
        return value;
    }
    
    public static MetaAlgorithm fromInt(int v)
    {
        MetaAlgorithm result = null;
        for (MetaAlgorithm ma: MetaAlgorithm.values())
        {
          if (v == ma.value)
          {
            result = ma;
            break;
          }
        }
        return result;
    }
}
