package com.whb;

import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;

import java.io.IOException;

public class MockReHeader implements Record {
    private long sessionId;
    private String type;
    public MockReHeader(long sessionId, String type) {
        this.sessionId = sessionId;
        this.type = type;
    }
    public MockReHeader() {

    }

    public long getSessionId() {
        return sessionId;
    }
    @Override
    public String toString() {
        return "sessionId = " + sessionId + ", type = " + type;
    }
    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void serialize(OutputArchive oa, String tag) throws IOException {
        oa.startRecord(this,tag);
        oa.writeLong(sessionId,"sessionId");
        oa.writeString(type,"type");
        oa.endRecord(this,tag);
    }

    @Override
    public void deserialize(InputArchive ia, String tag) throws IOException {
        ia.startRecord(tag);
        this.sessionId = ia.readLong("sessionId");
        this.type = ia.readString("type");
        ia.endRecord(tag);
    }
}
