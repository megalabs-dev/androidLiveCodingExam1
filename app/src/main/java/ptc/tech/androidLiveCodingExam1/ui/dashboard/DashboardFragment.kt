package ptc.tech.androidLiveCodingExam1.ui.dashboard

import android.os.Bundle
import android.view.*
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.composethemeadapter.MdcTheme
import ptc.tech.androidLiveCodingExam1.R
import ptc.tech.androidLiveCodingExam1.databinding.FragmentDashboardBinding
import ptc.tech.androidLiveCodingExam1.ui.dashboard.view.ExamView
import ptc.tech.androidLiveCodingExam1.ui.dashboard.viewModel.ExamViewModel

class DashboardFragment : Fragment() {

    private lateinit var examViewModel: ExamViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        examViewModel =
            ViewModelProvider(this).get(ExamViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val examView = root.findViewById<ComposeView>(R.id.exam_view)
        examViewModel.contacts.observe(viewLifecycleOwner, Observer {
            examView.setContent {
                MdcTheme { // or AppCompatTheme
                    ExamView(it.toList())
                }
            }
        })

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_nav_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_start -> {
                examViewModel.fetchContacts()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}