package ptc.tech.androidLiveCodingExam1.ui.home

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ptc.tech.androidLiveCodingExam1.R
import ptc.tech.androidLiveCodingExam1.data.ContactEntity
import ptc.tech.androidLiveCodingExam1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_nav_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_start -> {
                homeViewModel.fetchContacts()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val contactRecyclerView: RecyclerView = binding.rvContacts
        homeViewModel.contacts.observe(viewLifecycleOwner, Observer {
            val adapter = ContactListAdapter(it)
            contactRecyclerView.layoutManager = LinearLayoutManager(activity)
            contactRecyclerView.adapter = adapter
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class ContactListAdapter(private val dataSet: Array<ContactEntity>) :
    RecyclerView.Adapter<ContactListAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatarImageView: ImageView
        val fullNameTextView: TextView
        val adderssTextView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            avatarImageView = view.findViewById(R.id.imgView_avatar)
            fullNameTextView = view.findViewById(R.id.txtView_fullName)
            adderssTextView = view.findViewById(R.id.txtView_address)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.contact_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val contact = dataSet[position]
        viewHolder.fullNameTextView.text = String.format("%s %s", contact.firstName, contact.lastName)
        viewHolder.adderssTextView.text = String.format("%s, %s, %s, %s", contact.street, contact.city, contact.state, contact.country)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}