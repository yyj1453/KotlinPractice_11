package org.techtown.kotlinpractice_11

import androidx.appcompat.app.AppCompatActivity                                                     // appcompat 라이브러리를 사용하기 위함
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)                                                      // 액션바 아래에 콘텐츠 영역 출력
        supportActionBar?.setDisplayHomeAsUpEnabled(true)                                           // 액션바에 업버튼(백버튼) 생성
    }

    // 업버튼(백버튼) 구현
    override fun onSupportNavigateUp(): Boolean {                                                   // 업버튼이 눌렸을때 실행되는 함수
        Log.d("yyj", "onSupportNavigateUp")
        onBackPressed()                                                                             // 현재 액티비티 종료하고 이전 액티비티로 이동
        return super.onSupportNavigateUp()                                                          // 상위 클래스의 onSupportNavigateUp 함수 호출
    }

    // 액션아이템(메뉴구성) 구현
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {                                        // Menu라는 객체는 액션바의 메뉴바

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)                                                    // res폴더에있던 메뉴 리소스를 가져옴
        val menuItem = menu?.findItem(R.id.menu_search)
        val searchView = menuItem?.actionView as SearchView

        val menuItem1: MenuItem? = menu?.add(0, 0, 0, "menu1")              // 그룹아이디가 0, 메뉴아이디가 0, 순서가 0인 메뉴 아이템 추가
        val menuItem2: MenuItem? = menu?.add(0, 1, 1, "menu2")
        // 메뉴아이템은 그룹단위로 표시, itemId는 아이템의 아이디(이름) 역할, order은 메뉴에 표시되는 순서 (order값이 같을 경우엔 추가된 순서대로 표시)

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {                             // 검색어 변경 이벤트 (타자 하나하나 누를떄마다 작동)
                Log.d("yyj", "검색어 변경")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {                               // 키보드의 검색 버튼을 클릭한 순간의 이벤트
                Log.d("yyj", "검색 버튼 클릭")
                return true
            }
        })
        return true
    }

    // 메뉴 구성이 눌렸을 때 이벤트 처리
    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId){               // when은 자바의 switch문과 같음
        0 -> {
            Log.d("yyj", "menu1 click")
            true                                                                                    // 0번이 눌렸다는 의미에서 true를 반환, 추가적인 코드실행을 방지함으로서 안정성을 위한 코드
        }
        1 -> {
            Log.d("yyj", "menu2 click")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}