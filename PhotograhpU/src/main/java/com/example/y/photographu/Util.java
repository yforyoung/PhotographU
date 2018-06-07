package com.example.y.photographu;

import com.example.y.photographu.beans.Comment;
import com.example.y.photographu.beans.Topic;

import java.util.ArrayList;
import java.util.List;

public class Util {
    public List<Topic> getListTopic(){
        List<Topic> topicList = new ArrayList<>();
        topicList.add(new Topic(Test.getInstence().user1, "毕业啦，数据要多多多多多多多多多！",
                R.drawable.graduation,
                8,
                new ArrayList<Comment>(),
                Test.getInstence().type1));
        topicList.add(new Topic(Test.getInstence().user2, "cos！",
                R.drawable.cosplay,
                30,
                new ArrayList<Comment>(),
                Test.getInstence().type4));
        topicList.add(new Topic(Test.getInstence().user3, "个人拍！",
                R.drawable.personal,
                46,
                new ArrayList<Comment>(),
                Test.getInstence().type3));
        topicList.add(new Topic(Test.getInstence().user4, "古装照！",
                R.drawable.personal_index,
                48,
                new ArrayList<Comment>(),
                Test.getInstence().type2));
        topicList.add(new Topic(Test.getInstence().user5, "毕业照数据！",
                R.drawable.graduation,
                28,
                new ArrayList<Comment>(),
                Test.getInstence().type1));
        topicList.add(new Topic(Test.getInstence().user6, "四年，留下回忆的图书馆",
                R.drawable.people,
                2356,
                new ArrayList<Comment>(),
                Test.getInstence().type5));
        topicList.add(new Topic(Test.getInstence().user5, "活动会议！",
                R.drawable.roll_2,
                454,
                new ArrayList<Comment>(),
                Test.getInstence().type6));
        topicList.add(new Topic(Test.getInstence().user5, "图片处理需要加强！",
                R.drawable.roll_3,
                21,
                new ArrayList<Comment>(),
                Test.getInstence().type7));
        topicList.add(new Topic(Test.getInstence().user1, "个人，夜晚",
                R.drawable.tu6,
                10,
                new ArrayList<Comment>(),
                Test.getInstence().type3));
        topicList.add(new Topic(Test.getInstence().user2, "cos！",
                R.drawable.cosplay,
                6,
                new ArrayList<Comment>(),
                Test.getInstence().type4));
        topicList.add(new Topic(Test.getInstence().user3, "个人拍！",
                R.drawable.tu5,
                3,
                new ArrayList<Comment>(),
                Test.getInstence().type3));
        topicList.add(new Topic(Test.getInstence().user4, "古装照！",
                R.drawable.gu,
                46,
                new ArrayList<Comment>(),
                Test.getInstence().type2));
        topicList.add(new Topic(Test.getInstence().user4, "古装照！",
                R.drawable.tu2,
                84,
                new ArrayList<Comment>(),
                Test.getInstence().type2));

        topicList.add(new Topic(Test.getInstence().user5, "活动会议！",
                R.drawable.tu3,
                116,
                new ArrayList<Comment>(),
                Test.getInstence().type6));
        topicList.add(new Topic(Test.getInstence().user5, "测试数据！",
                R.drawable.tu4,
                3,
                new ArrayList<Comment>(),
                Test.getInstence().type7));
        return topicList;
    }
}
