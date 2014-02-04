package com.admarketplace.unsafe;

import java.math.BigDecimal;
import java.util.Vector;


public class Publisher
{
    public final Long ID;
    public final String name;
    public final int minLatencyInMillis;
    public final int latencyInMillis;
    public final boolean active;
    public final Integer queryCab;
    public final int maxMetaFeeds;
    public final int throttlePercent;
    public final boolean hasPriorityFeeds;
    public final boolean netPublisher;
    public final int revSharePercent;
    public final MetaAlgorithm metaAlgorithm;
    public final BigDecimal hardMinCPC;
    public final boolean adultOptOut;
    public final boolean cpv;
    public final int publisherTypeID;
    public final int cpaTestID;
    public final BigDecimal cpaTestNetCpc;
    public final BigDecimal cpaTestPercent;
    public final Integer cpaTestClickCap;
    public final Integer cpaTestSpendCap;
    public final BigDecimal cpaBackfillPercent;
    public final BigDecimal cpaBackfillNetCpc;
    public final int clickUrlVersion;
    public final BigDecimal fixedCpc;
    public final boolean feedQpsOverride;
    public final boolean feedCapsOverride;
    public final int dmaTagQueryThrottle;
    public final int haircutPercent;
    public final boolean capped;
    public final Integer dupQueryBlockTypeId;
    public final int kwHistoryPolicyId;
    public final Integer minProfileUpdateCpc000;
    public final Integer userProfilePolicyId;
    
    public final boolean readUserProfile;
    public final boolean writeUserProfile;
	public final boolean spellingEnabled;
    public final int spellingThrottle;
    
    public final BigDecimal smartRonCpcThreshold;
    public final int smartRonThrottle;
    
    public final boolean pubPixelEnabled;
    
    public final Vector<Long> feeds = new Vector<Long>();
    
    
    public Publisher(Long ID, String name, int minLatencyInMillis, int latencyInMillis, 
            int active, Integer queryCab, int maxMetaFeeds, Integer throttlePercent,
            boolean hasPriorityFeeds, boolean netPublisher, int revSharePercent, 
            int metaAlgorithm, BigDecimal hardMinCPC, boolean adultOptOut,
            boolean cpv, int publisherTypeID, int cpaTestID, BigDecimal cpaTestNetCpc,
            BigDecimal cpaTestPercent, Integer cpaTestClickCap, Integer cpaTestSpendCap, 
            BigDecimal cpaBackfillPercent, BigDecimal cpaBackfillNetCpc, int clickUrlVersion,
            BigDecimal fixedCpc, boolean feedQpsOverride, boolean feedCapsOverride, int dmaTagQueryThrottle,
            int haircutPercent, boolean capped, Integer dupQueryBlockTypeId,
            int kwHistoryPolicyId, Integer minProfileUpdateCpc000, Integer userProfilePolicyId,
		    boolean spellingEnabled, int spellingThrottle, BigDecimal smartRonCpcThreshold, int smartRonThrottle,
		    boolean pubPixelEnabled)
{
this.ID = ID;
this.name = name;
this.minLatencyInMillis = minLatencyInMillis;
this.latencyInMillis = latencyInMillis;
this.active = 1 == active;
this.queryCab = queryCab;
this.maxMetaFeeds = maxMetaFeeds;
this.throttlePercent = null == throttlePercent ? 100: throttlePercent;
this.hasPriorityFeeds = hasPriorityFeeds;
this.netPublisher = netPublisher;
this.revSharePercent = revSharePercent;
this.metaAlgorithm = MetaAlgorithm.fromInt(metaAlgorithm);
this.hardMinCPC = hardMinCPC;
this.adultOptOut = adultOptOut;
this.cpv = cpv;
this.publisherTypeID = publisherTypeID;
this.cpaTestID = cpaTestID; 
this.cpaTestNetCpc = cpaTestNetCpc;
this.cpaTestPercent = cpaTestPercent;
this.cpaTestClickCap = cpaTestClickCap;
this.cpaTestSpendCap = cpaTestSpendCap;
this.cpaBackfillPercent = cpaBackfillPercent;
this.cpaBackfillNetCpc = cpaBackfillNetCpc;
this.clickUrlVersion = clickUrlVersion;
this.fixedCpc = fixedCpc;
this.feedQpsOverride = feedQpsOverride;
this.feedCapsOverride = feedCapsOverride;
this.dmaTagQueryThrottle = dmaTagQueryThrottle;
this.haircutPercent = haircutPercent;
this.capped = capped;
this.dupQueryBlockTypeId = dupQueryBlockTypeId;
this.kwHistoryPolicyId = kwHistoryPolicyId;
this.minProfileUpdateCpc000 = minProfileUpdateCpc000;
this.userProfilePolicyId = userProfilePolicyId;
this.spellingEnabled = spellingEnabled;
this.spellingThrottle = spellingThrottle;
this.smartRonCpcThreshold = smartRonCpcThreshold;
this.smartRonThrottle = smartRonThrottle;
this.pubPixelEnabled = pubPixelEnabled;

if( userProfilePolicyId == 1 ) {
	this.readUserProfile  = false;
	this.writeUserProfile = false;
} else if( userProfilePolicyId == 2 ) {
	this.readUserProfile  = true;
	this.writeUserProfile = false;
} else {
	this.readUserProfile  = true;
	this.writeUserProfile = true;
}

}

	public void cleanup()
    {
    	feeds.clear();
    }
    
}
