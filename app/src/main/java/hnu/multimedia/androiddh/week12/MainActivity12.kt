package hnu.multimedia.androiddh.week12

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import hnu.multimedia.androiddh.databinding.ActivityMain12Binding

class MainActivity12 : AppCompatActivity() {

    private val binding by lazy { ActivityMain12Binding.inflate(layoutInflater) }
    private var currentSpots = mutableListOf<SpotModel>()
    private var remainSpots =  mutableListOf<SpotModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        remainSpots = getCards().toMutableList()
        currentSpots = remainSpots.shuffled().take(3).toMutableList()
        remainSpots.removeAll(currentSpots)

        val layoutManager = CardStackLayoutManager(this, object : CardStackListener {
            override fun onCardDragging(direction: Direction?, ratio: Float) {}
            override fun onCardSwiped(direction: Direction?) {}
            override fun onCardRewound() {}
            override fun onCardCanceled() {}
            override fun onCardAppeared(view: View?, position: Int) {}
            override fun onCardDisappeared(view: View?, position: Int) {}
        })

        binding.cardStackView.layoutManager = layoutManager
        val adapter = CardStackAdapter(currentSpots)
        binding.cardStackView.adapter = adapter

        binding.imageViewAdd.setOnClickListener {
            if (remainSpots.isNotEmpty()) {
                val newCard = remainSpots.removeAt(0)
                adapter.addCard(newCard)
            }
        }
        binding.imageViewLeft.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .build()
            layoutManager.setSwipeAnimationSetting(setting)
            binding.cardStackView.swipe()
        }
        binding.imageViewRight.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .build()
            layoutManager.setSwipeAnimationSetting(setting)
            binding.cardStackView.swipe()
        }
        binding.imageViewRewind.setOnClickListener {
            binding.cardStackView.rewind()
        }
    }

    private fun getCards(): List<SpotModel> {
        return listOf(
            SpotModel("서울","대한민국의 수도이다.", "https://wolf.community/files/attach/images/2022/05/28/c7ec9ec3bed48bbd0106289b0becf2c2.jpg"),
            SpotModel("뉴욕","자유의 여신상은 뉴욕뿐 아니라 미국전체를 대표하는 상징물로 맨해튼의 앞바다에 떠있는 리버티섬에 위치하고 있다.", "https://wolf.community/files/attach/images/2022/05/28/f8c3b52c72ddd219909627a6eb1df216.jpg"),
            SpotModel("오사카", "글리코멘 사인은 도톤보리에 위치한 마라토너를 형상화한 네온사인으로 1935년에 처음 등장하였다.", "https://wolf.community/files/attach/images/2022/05/28/ef6a34d8af4faf2b5c212ad50f3ee4dc.jpg"),
            SpotModel("방콕", "에메랄드 사원은 1784년에 완공되었으며 황금불탑의 사원으로 특히 서양인들에게 아주 인기가 높다.", "https://wolf.community/files/attach/images/2022/05/28/6958e77c753f4a14b9c5ee9e70cbac54.jpg"),
            SpotModel("타이페이", "101타워는 국제금융센터빌딩으로 2003년에 완공된 타이베이를 상징하는 건축물.", "https://wolf.community/files/attach/images/2022/05/28/061c38d4b6d8f32d078762c0d0519537.jpg"),
            SpotModel("상하이", "동방명주 탑은 1994년에 완공되었으며 인근의 400~600m급 슈퍼고층빌딩들과 어우러진 야경이 유명하다.", "https://wolf.community/files/attach/images/2022/05/28/f5f1e18e56f0e4523d65f03a183ef715.jpg"),
            SpotModel("모스크바", "성바실리 대성당은 붉은광장에 위치한 러시아정교회성당으로 1561년 완공되었다.", "https://wolf.community/files/attach/images/2022/05/28/f802f9b73364ee856970b124a8ca4e4e.jpg"),
            SpotModel("바티칸시티", "교황이 미사를 집전하는 전세계 가톨릭의 성지로 성베드로광장에 위치하고 있다.", "https://wolf.community/files/attach/images/2022/05/28/8c963fdbff1562141b5d078ccd005d4a.png"),
        )
    }
}