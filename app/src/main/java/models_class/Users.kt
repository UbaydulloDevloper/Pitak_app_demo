package models_class

import java.io.Serializable

class Users : Serializable {
    var idToken: String? = null
    var name: String? = null
    var imageUrl: String? = null
    var email: String? = null
    var online: Boolean? = null
    var newMessage: String? = null


    constructor(idToken: String?, name: String?, imageUrl: String?, email: String?) {
        this.idToken = idToken
        this.name = name
        this.imageUrl = imageUrl
        this.email = email
    }

    constructor()

    constructor(
        idToken: String?,
        name: String?,
        imageUrl: String?,
        email: String?,
        online: Boolean?,
        newMessage: String?
    ) {
        this.idToken = idToken
        this.name = name
        this.imageUrl = imageUrl
        this.email = email
        this.online = online
        this.newMessage = newMessage
    }

}