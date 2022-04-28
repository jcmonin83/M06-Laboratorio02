package pp.developer.myapplication.models

data class Usuario( var id: Int,
                    var nombre: String,
                    var usuario: String,
                    var correo: String,
                    var password: String,
                    var sexo: String,
                    var habilitado: Boolean
)
