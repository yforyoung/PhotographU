package com.example.y.photographu;

import com.example.y.photographu.beans.Photographer;
import com.example.y.photographu.beans.PhotographerComment;
import com.example.y.photographu.beans.Type;
import com.example.y.photographu.beans.User;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private static Test test;
    public User user1 = new User("小蕾爱摄影", R.drawable.head_1);
    public User user2 = new User("一只逆光Boy", R.drawable.head_2);
    public User user3 = new User("摄影飞熊", R.drawable.head_3);
    public User user4 = new User("文艺阿青", R.drawable.head_4);
    public User user5 = new User("知别林", R.drawable.head_5);
    public User user6 = new User("均难", R.drawable.head_6);

    public List<PhotographerComment>  photographerCommentList=new ArrayList<>();
    public List<Integer> photoList=new ArrayList<>();

    public Photographer photographer1=new Photographer(user1,199,"含100张底片和10张精修","欢迎来西华大学或附近的同学找我约拍哦~",
            photographerCommentList);
    public Photographer photographer2=new Photographer(user2,99,"含50张底片和6张精修",
            "这个人没有留下任何简介~",photographerCommentList);
    public Photographer photographer3=new Photographer(user3,159,"含50张底片和5张精修",
            "欢迎来西华大学或附近的同学找我约拍哦~",photographerCommentList);
    public Photographer photographer4=new Photographer(user4,100,"含40张底片和8张精修",
            "欢迎来西华大学或附近的同学找我约拍哦~",photographerCommentList);
    public Photographer photographer5=new Photographer(user5,299,"含200张底片和20张精修",
            "欢迎来西华大学或附近的同学找我约拍哦~",photographerCommentList);
    public Photographer photographer6=new Photographer(user6,80,"含30张底片和5张精修",
            "欢迎来西华大学或附近的同学找我约拍哦~",photographerCommentList);

    public Type type1= new Type("毕业照", "毕业照", R.drawable.graduation);
    public Type type2=new Type("古装", "古装", R.drawable.gu);
    public Type type3=new Type("个人写真", "个人写真", R.drawable.personal);
    public Type type4=new Type("Cosplay", "cosplay", R.drawable.cosplay);
    public Type type5= new Type("多人写真", "多人写真", R.drawable.people);
    public Type type6= new Type("活动会议", "活动会议", R.drawable.roll_2);
    public Type type7= new Type("商业圈", "商业圈", R.drawable.roll_3);

    public static Test getInstence() {
        if (test == null)
            return new Test();
        else
            return test;
    }

}
