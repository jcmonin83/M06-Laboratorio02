package pp.developer.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pp.developer.myapplication.models.Restaurante
import pp.developer.myapplication.models.Usuario
import pp.developer.myapplication.services.APIServices
import pp.developer.myapplication.services.GetRetrofit
import retrofit2.Response
import retrofit2.Retrofit

class Login : AppCompatActivity() {
    private val USER_ID:String = "id"
    private val USER_USER:String = "usuario"
    private lateinit var retroApi: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        retroApi = GetRetrofit().getRetrofit()
    }

    fun login_Click(view: View) {
        val usuario = findViewById<EditText>(R.id.iTxtUsuario).text
        val contrasena = findViewById<EditText>(R.id.iTxtContrasena).text
        if( !usuario.isNullOrEmpty() && !contrasena.isNullOrEmpty() ){
            validateUsuario("validateUsuario")
        }else{
            Toast.makeText(this,"Usuario no valido.",Toast.LENGTH_SHORT).show()
        }
    }
    fun validateUsuario(path: String){
        var id:Int = 0
        var usuario:String = ""
        //Toast.makeText(this,"Validando usuario...", Toast.LENGTH_SHORT).show()
        Message("Validando usuario...")

            CoroutineScope(Dispatchers.IO).launch {
                val call: Response<Usuario> = retroApi.create(APIServices::class.java).validateUser(path)
                val rest:Usuario? = call.body()
                runOnUiThread {
                    if(call.isSuccessful){
                        id = rest!!.id
                        usuario = rest!!.usuario
                    }else{
                        Message("Error: Carga de informacion")
                    }
                }
            }
            val intent = Intent(this,MainActivity::class.java).apply {
                putExtra(USER_ID,id)
                putExtra(USER_USER,usuario)
            }
            startActivity(intent)

    }
    private fun Message( message:String){
        Toast.makeText(this,"${message}",Toast.LENGTH_SHORT).show()
    }
    fun validateEmail(email: String): Boolean {
        val pattern: String = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+\$"
        val regExp: Regex = Regex(pattern)
        return regExp.matches(email)
    }
    fun validatePassword(password: String): Boolean{
        val pattern: String = "^[a-z0-9]{6}\$"
        val regExp: Regex = Regex(pattern)
        return regExp.matches(password)
    }

    fun txtV_Registro_Click(view: View) {
        val intent = Intent(this,RegistroUsuarioActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}