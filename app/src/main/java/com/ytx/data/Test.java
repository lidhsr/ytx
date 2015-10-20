package com.ytx.data;

import org.json.JSONObject;
import org.kymjs.kjframe.data.Entity;

/**
 * Created by Augustus on 15/10/20.
 */
public class Test extends Entity implements Entity.Builder<Test> {

    private static Test test;

    public String name;

    public static Builder<Test> getInfo() {
        if(null == test) {
            test = new Test();
        }
        return test;
    }

    @Override
    public Test create(JSONObject jsonObject) {
        Test test = new Test();
        test.name = jsonObject.optString("name");

        return test;
    }
}
