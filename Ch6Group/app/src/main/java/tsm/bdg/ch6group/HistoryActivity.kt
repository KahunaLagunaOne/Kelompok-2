package tsm.bdg.ch6group

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tsm.bdg.ch6group.databinding.ActivityHistoryBinding

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding


    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityHistoryBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val db = Database1.getInstance(this)

        GlobalScope.launch(Dispatchers.IO) {
//            val history = db?.gameDao()?.getAllDataGame()
            val history = db?.gameDao()?.getHistory()

            launch(Dispatchers.Main) {

                if (history != null) {

                    val adapterHistoryActivity = RvAdapter(history)

                    val layoutManagerHistoryActivity = LinearLayoutManager(
                        this@HistoryActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )

                    binding.rvHistory.layoutManager = layoutManagerHistoryActivity

                    binding.rvHistory.adapter = adapterHistoryActivity


                }
            }
        }
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}