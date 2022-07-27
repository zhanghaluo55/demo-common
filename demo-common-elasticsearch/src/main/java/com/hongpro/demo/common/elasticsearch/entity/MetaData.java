package com.hongpro.demo.common.elasticsearch.entity;

import java.util.concurrent.TimeUnit;

import org.elasticsearch.common.unit.ByteSizeUnit;

/**
 * 元数据载体类
 */
public class MetaData{
    private String indexName = "";
    private String indextype = "";
    private String[] searchIndexNames;
    private int numberOfShards;
    private int numberOfReplicas;
    private boolean printLog = false;
    private boolean alias;
    private String[] aliasIndex;
    private String writeIndex;
    private boolean rollover;
    private long rolloverMaxIndexAgeCondition;
    private TimeUnit rolloverMaxIndexAgeTimeUnit;
    private long rolloverMaxIndexDocsCondition;
    private long rolloverMaxIndexSizeCondition;
    private ByteSizeUnit rolloverMaxIndexSizeByteSizeUnit;
    private boolean autoRollover;
    private long autoRolloverInitialDelay;
    private long autoRolloverPeriod;
    private TimeUnit  autoRolloverTimeUnit;
    /**
     * indexName的后缀，一般用于配置中环境的区分
     */
    private String suffix;
    private boolean autoCreateIndex;
    private long maxResultWindow;
    private String settingsPath;
    private boolean isScore;

    public MetaData(String indexName, String indextype) {
        this.indexName = indexName;
        this.indextype = indextype;
    }

    public MetaData(String indexName, String indextype, int numberOfShards, int numberOfReplicas) {
        this.indexName = indexName;
        this.indextype = indextype;
        this.numberOfShards = numberOfShards;
        this.numberOfReplicas = numberOfReplicas;
    }

    public MetaData(int numberOfShards, int numberOfReplicas) {
        this.numberOfShards = numberOfShards;
        this.numberOfReplicas = numberOfReplicas;
    }

    public String[] getSearchIndexNames() {
        return searchIndexNames;
    }
    public void setSearchIndexNames(String[] searchIndexNames) {
        this.searchIndexNames = searchIndexNames;
    }
    public boolean isPrintLog() {
        return printLog;
    }
    public void setPrintLog(boolean printLog) {
        this.printLog = printLog;
    }
    public String getIndexName() {
        return indexName;
    }
    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }
    public String getIndextype() {
        return indextype;
    }
    public void setIndextype(String indextype) {
        this.indextype = indextype;
    }
    public int getNumberOfShards() {
        return numberOfShards;
    }
    public void setNumberOfShards(int numberOfShards) {
        this.numberOfShards = numberOfShards;
    }
    public int getNumberOfReplicas() {
        return numberOfReplicas;
    }
    public void setNumberOfReplicas(int numberOfReplicas) {
        this.numberOfReplicas = numberOfReplicas;
    }
    public long getMaxResultWindow() {
        return maxResultWindow;
    }
    public void setMaxResultWindow(long maxResultWindow) {
        this.maxResultWindow = maxResultWindow;
    }
    public boolean isAlias() {
        return alias;
    }
    public void setAlias(boolean alias) {
        this.alias = alias;
    }
    public String[] getAliasIndex() {
        return aliasIndex;
    }
    public void setAliasIndex(String[] aliasIndex) {
        this.aliasIndex = aliasIndex;
    }
    public String getWriteIndex() {
        return writeIndex;
    }
    public void setWriteIndex(String writeIndex) {
        this.writeIndex = writeIndex;
    }
    public boolean isRollover() {
        return rollover;
    }
    public void setRollover(boolean rollover) {
        this.rollover = rollover;
    }
    public long getRolloverMaxIndexAgeCondition() {
        return rolloverMaxIndexAgeCondition;
    }
    public void setRolloverMaxIndexAgeCondition(long rolloverMaxIndexAgeCondition) {
        this.rolloverMaxIndexAgeCondition = rolloverMaxIndexAgeCondition;
    }
    public TimeUnit getRolloverMaxIndexAgeTimeUnit() {
        return rolloverMaxIndexAgeTimeUnit;
    }
    public void setRolloverMaxIndexAgeTimeUnit(TimeUnit rolloverMaxIndexAgeTimeUnit) {
        this.rolloverMaxIndexAgeTimeUnit = rolloverMaxIndexAgeTimeUnit;
    }
    public long getRolloverMaxIndexDocsCondition() {
        return rolloverMaxIndexDocsCondition;
    }
    public void setRolloverMaxIndexDocsCondition(long rolloverMaxIndexDocsCondition) {
        this.rolloverMaxIndexDocsCondition = rolloverMaxIndexDocsCondition;
    }
    public long getRolloverMaxIndexSizeCondition() {
        return rolloverMaxIndexSizeCondition;
    }
    public void setRolloverMaxIndexSizeCondition(long rolloverMaxIndexSizeCondition) {
        this.rolloverMaxIndexSizeCondition = rolloverMaxIndexSizeCondition;
    }
    public ByteSizeUnit getRolloverMaxIndexSizeByteSizeUnit() {
        return rolloverMaxIndexSizeByteSizeUnit;
    }
    public void setRolloverMaxIndexSizeByteSizeUnit(ByteSizeUnit rolloverMaxIndexSizeByteSizeUnit) {
        this.rolloverMaxIndexSizeByteSizeUnit = rolloverMaxIndexSizeByteSizeUnit;
    }
    public boolean isAutoRollover() {
        return autoRollover;
    }
    public void setAutoRollover(boolean autoRollover) {
        this.autoRollover = autoRollover;
    }
    public long getAutoRolloverInitialDelay() {
        return autoRolloverInitialDelay;
    }
    public void setAutoRolloverInitialDelay(long autoRolloverInitialDelay) {
        this.autoRolloverInitialDelay = autoRolloverInitialDelay;
    }

    public long getAutoRolloverPeriod() {
        return autoRolloverPeriod;
    }

    public void setAutoRolloverPeriod(long autoRolloverPeriod) {
        this.autoRolloverPeriod = autoRolloverPeriod;
    }

    public TimeUnit getAutoRolloverTimeUnit() {
        return autoRolloverTimeUnit;
    }

    public void setAutoRolloverTimeUnit(TimeUnit autoRolloverTimeUnit) {
        this.autoRolloverTimeUnit = autoRolloverTimeUnit;
    }


    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public boolean isAutoCreateIndex() {
        return autoCreateIndex;
    }

    public void setAutoCreateIndex(boolean autoCreateIndex) {
        this.autoCreateIndex = autoCreateIndex;
    }

    public String getSettingsPath() {
        return settingsPath;
    }

    public void setSettingsPath(String settingsPath) {
        this.settingsPath = settingsPath;
    }

    public boolean getIsScore() {
        return isScore;
    }

    public void setIsScore(boolean score) {
        isScore = score;
    }
}