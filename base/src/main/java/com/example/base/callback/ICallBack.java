package com.example.base.callback;

import com.example.base.entity.Person;

import java.util.List;

public interface ICallBack {
    interface DataBaseCallBack{
        void state(boolean b);
        void quertResult(List<Person> personList);
    }
}
