package tsm.bdg.ch6group

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityLoginBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val clickSignUp = binding.tvSignUpActivityLogin

        clickSignUp.setOnClickListener {

            val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(intent)

        }

        binding.btnSignIn.setOnClickListener {

            var nameInput = binding.etUserNameActivityLogin.text.toString()
            var passwordInput = binding.etPasswordActivityLogin.text.toString()

            if (nameInput.isEmpty() || passwordInput.isEmpty()) {
                Toast.makeText(
                    this@LoginActivity,
                    "Type your name and password",
                    Toast.LENGTH_SHORT
                ).show()

                binding.etUserNameActivityLogin.text.clear()
                binding.etPasswordActivityLogin.text.clear()


            } else {
//                Perform query
                val db = Db.getInstance(this)

                GlobalScope.launch(Dispatchers.IO) {

                    val tryLogin = db?.userDao()?.login(nameInput, passwordInput)

                    if (tryLogin == null) {

                        GlobalScope.launch(Dispatchers.Main) {
                            Toast.makeText(
                                this@LoginActivity,
                                "Incorrect name or incorrect password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        binding.etUserNameActivityLogin.text.clear()
                        binding.etPasswordActivityLogin.text.clear()

                    } else {

                        GlobalScope.launch(Dispatchers.Main) {

                            binding.etUserNameActivityLogin.text.clear()
                            binding.etPasswordActivityLogin.text.clear()

                            val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                            intent.putExtra("name", nameInput)
                            startActivity(intent)

                        }


                    }
                }


            }


        }

    }

}