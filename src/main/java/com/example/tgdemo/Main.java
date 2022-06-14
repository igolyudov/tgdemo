package com.example.tgdemo;

import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class Main {

    private Integer apiId = 94575;
    private String apiHash = "a3406de8d171bb422bb6ddf3bbd800e2";

    public static void main(String[] args) {


            try {
                System.load("/opt/tdlib/libtdjni.so");
            } catch (UnsatisfiedLinkError e) {
                log.error("Error",e);
            }

        Main main = new Main();
        main.init();
    }

    private void runClient( String phone)
    {
        TelegramClient client =  new TelegramClient(apiId,apiHash, phone);
        client.start();
        try{
            Thread.sleep(4000);
        }
        catch (Exception e)
        {}
    }

    public void init()
    {
        log.debug("Init clients");
        try {
            List<String> accountsList = Files.readAllLines(Paths.get("/tmp/accounts.txt"));
            log.debug("Have {} Telegram clients", accountsList.size());
            for (String account : accountsList) {
                log.debug("init {}", account);
                runClient( account);
            }
        }
        catch (Exception e)
        {
            log.error("Error in read accounts file",e);
        }
    }
}
