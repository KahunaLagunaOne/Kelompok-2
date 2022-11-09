package tsm.bdg.ch6group

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.R.color.red
import tsm.bdg.ch6group.R.color.teal_200
import tsm.bdg.ch6group.databinding.ActivitySignUpBinding
import tsm.bdg.ch6group.databinding.CustomRegisterDialogBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var view: CustomRegisterDialogBinding

    private lateinit var binding: ActivitySignUpBinding

    private var dataAvatar: String = "1"

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {


        binding = ActivitySignUpBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        view = CustomRegisterDialogBinding.inflate(LayoutInflater.from(this),null,false)
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setView(view.root)
        val dialog = dialogBuilder.create()

        val db = Db.getInstance(this)

        var srcImage1: ImageButton = binding.ivAvatar1ActivitySignUp
        var srcImage2: ImageButton = binding.ivAvatar2ActivitySignUp
        var srcImage3: ImageButton = binding.ivAvatar3ActivitySignUp
        var srcImage4: ImageButton = binding.ivAvatar4ActivitySignUp


        srcImage1.setOnClickListener {

//             selected
            srcImage1.setBackgroundResource(R.drawable.bg_rounded)

//            unselected
            srcImage2.setBackgroundResource(R.drawable.bg_default)
            srcImage3.setBackgroundResource(R.drawable.bg_default)
            srcImage4.setBackgroundResource(R.drawable.bg_default)

            dataAvatar = "1"

        }

        srcImage2.setOnClickListener {

//             selected
            srcImage2.setBackgroundResource(R.drawable.bg_rounded)
//            unselected
            srcImage1.setBackgroundResource(R.drawable.bg_default)
            srcImage3.setBackgroundResource(R.drawable.bg_default)
            srcImage4.setBackgroundResource(R.drawable.bg_default)

            dataAvatar = "2"

        }

        srcImage3.setOnClickListener {

//             selected
            srcImage3.setBackgroundResource(R.drawable.bg_rounded)
//            unselected
            srcImage2.setBackgroundResource(R.drawable.bg_default)
            srcImage1.setBackgroundResource(R.drawable.bg_default)
            srcImage4.setBackgroundResource(R.drawable.bg_default)

            dataAvatar = "3"


        }

        srcImage4.setOnClickListener {

//             selected
            srcImage4.setBackgroundResource(R.drawable.bg_rounded)
//            unselected
            srcImage2.setBackgroundResource(R.drawable.bg_default)
            srcImage3.setBackgroundResource(R.drawable.bg_default)
            srcImage1.setBackgroundResource(R.drawable.bg_default)

            dataAvatar = "4"

        }


        binding.btnSignUpActivitySignUp.setOnClickListener {
            val dataName = binding.etUserNameActivitySignUp.text.trim().toString()
            val dataEmail = binding.etEmailActivitySignUp.text.trim().toString()
            val dataPassword = binding.etEnterPasswordActivitySignUp.text.trim().toString()
            val dataPassword1 = binding.etReEnterPasswordActivitySignUp.text.trim().toString()

            if (dataPassword != dataPassword1) {
                Toast.makeText(
                    this@SignUpActivity,
                    "Make sure both password are the same !",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                if (dataName.isEmpty() ||
                    dataEmail.isEmpty() ||
                    dataPassword.isEmpty() ||
                    dataAvatar.isEmpty()
                ) {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Choose Avatar, Enter Username, Email and Password !",
                        Toast.LENGTH_SHORT
                    ).show()

                    this.clearText()


                } else {
                    val dataDb = User(
                        name = dataName,
                        email = dataEmail,
                        password = dataPassword,
                        avatar = dataAvatar

                    )

                    GlobalScope.launch(Dispatchers.IO) {
                        val status = db?.userDao()
                            ?.insert(dataDb) ?: 0

                        Log.e("status", status.toString())
                        if (status >= 1) {
                            launch(Dispatchers.Main) {
                                dialog.show()
                            }
                        }
                    }
                    this.clearText()
                }
            }
        }

        view.btnNext.setOnClickListener {
            finish()
            dialog.dismiss()
        }

        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    private fun clearText() {
        binding.etUserNameActivitySignUp.text.clear()
        binding.etEmailActivitySignUp.text.clear()
        binding.etEnterPasswordActivitySignUp.text.clear()
        binding.etReEnterPasswordActivitySignUp.text.clear()
    }
}