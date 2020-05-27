package com.yash.spider_task_3;

public class item_creator {
    private String player1_name;
    private String player2_name;
    private String winner;
    public item_creator(String player1Name, String player2Name,String Winner) {
       player1_name=player1Name;
       player2_name=player2Name;
       winner=Winner;
    }

    public String getPlayer1_name() {
        return player1_name;
    }

    public String getPlayer2_name() {
        return player2_name;
    }

    public String getWinner() {
        return winner;
    }
}
