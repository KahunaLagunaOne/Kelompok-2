package tsm.bdg.ch6group

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding


    private var dataImage : String = "1"

    @SuppressLint("CommitPrefEdits", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityHomeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val sharedPreference = getSharedPreferences("Setting", MODE_PRIVATE)

        val sharedEdit = sharedPreference.edit()

        sharedEdit.putBoolean("checked", false)

        val player = intent.getStringExtra("name")



        binding.tvNameActivitySignUp.text = player

        val db = Db.getInstance(this)

        GlobalScope.launch(Dispatchers.IO) {

            var avatar = db?.userDao()?.getName(player.toString())

            launch(Dispatchers.Main) {

                dataImage = avatar?.avatar.toString()

                when (dataImage) {
                    "1" -> {
                        binding.ivAvatarActivitySignUp.setImageResource(R.drawable.avatar1_svgrepo_com)

                    }
                    "2" -> {
                        binding.ivAvatarActivitySignUp.setImageResource(R.drawable.avatar2_svgrepo_com)

                    }
                    "3" -> {
                        binding.ivAvatarActivitySignUp.setImageResource(R.drawable.avatar3_svgrepo_com)

                    }
                    "4" -> {
                        binding.ivAvatarActivitySignUp.setImageResource(R.drawable.avatar4_svgrepo_com)

                    }

                }


            }

        }





        binding.btnExitActivityHome.setOnClickListener {
            finish()
        }

        binding.btnPlayGameActivityHome.setOnClickListener {
            val intent = Intent(this, HalamanMenuActivity::class.java)
            intent.putExtra("name", player)
            startActivity(intent)


        }

        binding.btnSettingActivityHome.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }

        binding.btnProfileActivitySignUp.setOnClickListener {

            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("name", player)
            intent.putExtra("imageAvatar", dataImage)

            startActivity(intent)

        }

        binding.btnHelpActivityHome.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }

        binding.btnHistoryActivityHome.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }


    }
}