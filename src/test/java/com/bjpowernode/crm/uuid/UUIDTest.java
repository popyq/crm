package com.bjpowernode.crm.uuid;

import java.util.UUID;

/**
 * @Author potato
 * @PackageName:com.bjpowernode.crm.uuid
 * @Description: UUID
 * @Date 2023-04-12 10:51
 */

public class UUIDTest {
    public static void main(String[] args) {
        String string = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(string);

    }
}
