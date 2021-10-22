package com.whb;

import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;
import org.apache.zookeeper.server.ByteBufferInputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

public class ZookeeperSeriazlize {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BinaryOutputArchive boa = BinaryOutputArchive.getArchive(baos);
        new MockReHeader(0x3421eccb92a34el, "ping").serialize(boa, "header");

        ByteBuffer bb = ByteBuffer.wrap(baos.toByteArray());

        ByteBufferInputStream bbis = new ByteBufferInputStream(bb);
        BinaryInputArchive bia = BinaryInputArchive.getArchive(bbis);

        MockReHeader header2 = new MockReHeader();
        System.out.println(header2);
        header2.deserialize(bia, "header");
        System.out.println(header2);
        bbis.close();
        baos.close();
    }
}
