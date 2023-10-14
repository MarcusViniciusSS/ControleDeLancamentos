package webapi.plugins.viewModels

import kotlinx.serialization.Serializable

@Serializable
class UserViewModel (val username:String, val password:String) {
}