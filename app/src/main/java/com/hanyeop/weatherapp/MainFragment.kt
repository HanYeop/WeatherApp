package com.hanyeop.weatherapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.hanyeop.weatherapp.databinding.FragmentMainBinding
import com.hanyeop.weatherapp.util.Constants.Companion.TAG
import com.hanyeop.weatherapp.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    // 뷰모델 생성
    private val viewModel by viewModels<WeatherViewModel>()

    // 참조 관리
    private var _binding : FragmentMainBinding? = null
    private val binding get() = _binding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 뷰바인딩
        _binding = FragmentMainBinding.bind(view)

        viewModel.getWeather("JSON",10,1,
        20210621,1100,"55","127")

        viewModel.weatherResponse.observe(viewLifecycleOwner){
            Log.d(TAG, "${it.body()}")
        }
    }

    // 프래그먼트는 뷰보다 오래 지속 . 프래그먼트의 onDestroyView() 메서드에서 결합 클래스 인스턴스 참조를 정리
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}