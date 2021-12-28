package com.example.mentomenti

import java.util.*

class Message {
    constructor(){

    }

    constructor(message:String,email:String,imageUrl:String,name:String){
        this.message=message
        this.email=email // 이메일을 받는 생성자
        this.imageUrl=imageUrl
        this.name=name
        date= Date()
    }


    var email:String=""
    var name:String=""
    var message:String=""
    var date:Date=Date()
    var id:String=""
    var imageUrl:String=""
}