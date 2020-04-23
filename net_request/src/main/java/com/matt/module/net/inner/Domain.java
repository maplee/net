package com.matt.module.net.inner;

/**
 * Author:Created by matt on 2020/3/3.
 * Email:jiagfone@163.com
 */

public class Domain {

    public static final int RELEASE = 2;
    public static final int PRE_RELEASE = 1;
    public static final int TEST = 0;
    public static final int CUSTOM = -1;

    //check
    public static final String RELEASE_CHECK_URL = "http://check.app.matt.com/";
    public static final String PRE_RELEASE_CHECK_URL = "http://check.app.32solo.com/";
    public static final String TEST_CHECK_URL = "http://check.app.36solo.com/";

    //api
    public static final String RELEASE_API_URL = "http://api.app.matt.com/";
    public static final String PRE_RELEASE_API_URL = "http://api.app.32solo.com/";
    public static final String TEST_API_URL = "http://api.app.36solo.com/";

    //socket
    public static final String RELEASE_SOCKET_URL = "http://socket.matt.com/";
    public static final String PRE_RELEASE_SOCKET_URL = "http://socket.32solo.com/";
    public static final String TEST_SOCKET_URL = "http://socket.36solo.com/";

    //api.game
    public static final String RELEASE_API_GAME_URL = "http://api.game.matt.com/";
    public static final String PRE_RELEASE_API_GAME_URL = "http://api.game.32solo.com/";
    public static final String TEST_API_GAME_URL = "http://api.game.36solo.com/";

    //game
    public static final String RELEASE_GAME_URL = "http://game.matt.com/";
    public static final String PRE_RELEASE_GAME_URL = "http://game.32solo.com/";
    public static final String TEST_GAME_URL = "http://game.36solo.com/";

    //api.erp
    public static final String RELEASE_API_ERP_URL = "http://api.erp.matt.com/";
    public static final String PRE_RELEASE_API_ERP_URL = "http://api.erp.32solo.com/";
    public static final String TEST_API_ERP_URL = "http://api.erp.36solo.com/";

    //erp
    public static final String RELEASE_ERP_URL = "http://erp.matt.com/";
    public static final String PRE_RELEASE_ERP_URL = "http://erp.32solo.com/";
    public static final String TEST_ERP_URL = "http://erp.36solo.com/";


    public static String getCheckUrl(int condition) {
        if(condition == RELEASE){
            return RELEASE_CHECK_URL;
        }else if(condition == PRE_RELEASE){
            return PRE_RELEASE_CHECK_URL;
        }else{
            return TEST_CHECK_URL;
        }
    }

    public static String getApiUrl(int condition) {
        if(condition == RELEASE){
            return RELEASE_API_URL;
        }else if(condition == PRE_RELEASE){
            return PRE_RELEASE_API_URL;
        }else{
            return TEST_API_URL;
        }
    }


    public static String getSocketUrl(int condition) {
        if(condition == RELEASE){
            return RELEASE_SOCKET_URL;
        }else if(condition == PRE_RELEASE){
            return PRE_RELEASE_SOCKET_URL;
        }else{
            return TEST_SOCKET_URL;
        }
    }


    public static String getApiGameUrl(int condition) {
        if(condition == RELEASE){
            return RELEASE_API_GAME_URL;
        }else if(condition == PRE_RELEASE){
            return PRE_RELEASE_API_GAME_URL;
        }else{
            return TEST_API_GAME_URL;
        }
    }


    public static String getGameUrl(int condition) {
        if(condition == RELEASE){
            return RELEASE_GAME_URL;
        }else if(condition == PRE_RELEASE){
            return PRE_RELEASE_GAME_URL;
        }else{
            return TEST_GAME_URL;
        }
    }

    public static String getGameUrl(int condition,String customUrl) {
        if(condition == CUSTOM){
            return customUrl;
        }else{
            return getGameUrl(condition);
        }
    }

    public static String getApiErpUrl(int condition) {
        if(condition == RELEASE){
            return RELEASE_API_ERP_URL;
        }else if(condition == PRE_RELEASE){
            return PRE_RELEASE_API_ERP_URL;
        }else{
            return TEST_API_ERP_URL;
        }
    }


    public static String getErpUrl(int condition) {
        if(condition == RELEASE){
            return RELEASE_ERP_URL;
        }else if(condition == PRE_RELEASE){
            return PRE_RELEASE_ERP_URL;
        }else{
            return TEST_ERP_URL;
        }
    }




}
