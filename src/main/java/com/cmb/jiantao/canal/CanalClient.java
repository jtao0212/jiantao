package com.cmb.jiantao.canal;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.cmb.jiantao.entity.User;
import com.cmb.jiantao.utils.CanalDataHandler;
import com.google.protobuf.InvalidProtocolBufferException;

import java.net.InetSocketAddress;
import java.util.List;

public class CanalClient {

    private static String SERVER_ADDRESS = "127.0.0.1";

    private static Integer PORT = 11111;

    private static String DESTINATION = "example";

    private static String USERNAME = "";

    private static String PASSWORD = "";

    public static void main(String[] args) {
        CanalConnector canalConnector = CanalConnectors.newSingleConnector(new InetSocketAddress(SERVER_ADDRESS, PORT), DESTINATION, USERNAME, PASSWORD);
        canalConnector.connect();
        canalConnector.subscribe(".*\\..*");
        for (; ; ) {
            Message message = canalConnector.getWithoutAck(100);
            long batchId = message.getId();
            if (batchId != -1) {
                System.out.println("batchId----->" + batchId);
                printEntity(message.getEntries());
            }
        }
    }

    private static void printEntity(List<CanalEntry.Entry> entries) {
        for (CanalEntry.Entry entry : entries) {
            if (entry.getEntryType() != CanalEntry.EntryType.ROWDATA) {
                continue;
            }
            try {
                CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                for (CanalEntry.RowData rowData : rowChange.getRowDatasList()) {
                    switch (rowChange.getEventType()) {
                        case INSERT:
                            String tableName = entry.getHeader().getTableName();
                            User insertUser = CanalDataHandler.ConvertToBean(rowData.getAfterColumnsList(), User.class);
                            System.out.println(insertUser);
                            System.out.println("This is insert!\n");
                            break;
                        case DELETE:
                            User deleteUser = CanalDataHandler.ConvertToBean(rowData.getBeforeColumnsList(), User.class);
                            System.out.println(deleteUser);
                            System.out.println("This is delete!\n");
                            break;
                        case UPDATE:
                            User updateUser = CanalDataHandler.ConvertToBean(rowData.getAfterColumnsList(), User.class);
                            System.out.println(updateUser);
                            System.out.println("This is update!\n");
                            break;
                        default:
                            break;
                    }
                }
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }
        }
    }
}
