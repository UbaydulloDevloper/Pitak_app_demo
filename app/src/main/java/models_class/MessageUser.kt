package models_class

class MessageUser {
    var myId: String? = null
    var youId: String? = null
    var date: String? = null
    var massage: String? = null

    constructor(myId: String?, youId: String?, date: String?, massage: String?) {
        this.myId = myId
        this.youId = youId
        this.date = date
        this.massage = massage
    }

    constructor()

}