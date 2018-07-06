package com.alorma.tempcontacts.ui.contacts

/*
class ContactsListFragment : Fragment() {

    @Inject
    lateinit var navigator: ContactsNavigator

    @Inject
    lateinit var viewModel: ContactsListViewModel

    private lateinit var adapter: DslAdapter<Contact>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.contacts_list_fragment, null, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        component add ContactsListModule(this) inject this

        viewModel.subscribe(this)

        viewModel.contacts.observe(this, Observer {
            it?.let { onState(it) }
        })

        adapter = adapterDsl(view.recycler) {
            item {
                layout = android.R.layout.simple_list_item_1
                bindView { view, contact ->
                    view.findViewById<TextView>(android.R.id.text1)?.text = contact.name
                }
            }
        }
        view.recycler.layoutManager = LinearLayoutManager(context)

        view.fab.setOnClickListener { navigator.openCreateContact() }
    }

    private fun onState(it: ContactsList.ContactsState) {
        when (it) {
            is ContactsList.ContactsState.Items -> onItems(it)
        }
    }

    private fun onItems(it: ContactsList.ContactsState.Items) {
        adapter.submitList(it.items)
    }

}*/