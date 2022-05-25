package com.jamessaboia.prodmaisapp.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.jamessaboia.prodmaisapp.Constants.Constants
import com.jamessaboia.prodmaisapp.Model.Login
import com.jamessaboia.prodmaisapp.Model.TaskPost
import com.jamessaboia.prodmaisapp.R
import com.jamessaboia.prodmaisapp.ViewModel.TaskViewModel
import com.jamessaboia.prodmaisapp.databinding.FragmentCreateNotesBinding
import org.w3c.dom.Text
import kotlin.random.Random


class CreateNotesFragment : Fragment(), AdapterView.OnItemSelectedListener {

    lateinit var binding: FragmentCreateNotesBinding
//  var priority: String = "1"
    val viewModel: TaskViewModel by viewModels()

    private var status: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)

//        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
//
//        binding.pGreen.setOnClickListener {
//            priority = "1"
//            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
//            binding.pYellow.setImageResource(0)
//            binding.pRed.setImageResource(0)
//        }
//        binding.pYellow.setOnClickListener {
//            priority = "2"
//            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
//            binding.pGreen.setImageResource(0)
//            binding.pRed.setImageResource(0)
//        }
//        binding.pRed.setOnClickListener {
//            priority = "3"
//            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
//            binding.pGreen.setImageResource(0)
//            binding.pYellow.setImageResource(0)
//        }

//        val spinnerValues = resources.getStringArray(R.array.spinner_values)
//        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.spinner_items, spinnerValues)
//        arrayAdapter.setDropDownViewResource(R.layout.spinner_items)
//        binding.edtStatus.setAdapter(arrayAdapter)

        val adapter: ArrayAdapter<*> =
            ArrayAdapter.createFromResource(requireContext(), R.array.spinner_values, R.layout.spinner_items)
        adapter.setDropDownViewResource(R.layout.spinner_items)
        binding.edtStatus.setAdapter(adapter)

        binding.edtStatus.onItemSelectedListener = this

        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }

        return binding.root
    }

    private fun createNotes(it: View?) {

        val title = binding.edtTitle.text.toString()
        val notes = binding.edtNotes.text.toString()

        if (title.isEmpty() || title.isBlank()){
            Toast.makeText(requireContext(), "Adicione um título a tarefa!", Toast.LENGTH_SHORT).show()
        } else {

            var data: TaskPost? = null

            if(notes.isEmpty() || notes.isBlank()){
                data = TaskPost(
                    title,
                    null,
                    status
                )
            } else {
                data = TaskPost(
                    title,
                    notes,
                    status
                )
            }

            Login.token?.let { it1 -> Login.idBoard?.let { it2 ->
                viewModel.postTask(it1,
                    it2, data)
            } }

            Toast.makeText(requireContext(), "Tarefa Criada com Sucesso!", Toast.LENGTH_SHORT).show()

            if(status == 3){

                val view = View.inflate(this.requireContext(), R.layout.dialog_quotes, null)


                val builder = AlertDialog.Builder(this.requireContext())
                builder.setView(view)

                val dialog = builder.create()
                dialog.show()
                dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

                val textMotivacional = view.findViewById<TextView>(R.id.tv_motivational)
                textMotivacional.setText(Constants.listMessage.get(Random.nextInt(0, Constants.listMessage.size - 1)))

                 val buttonConfirm = view.findViewById<MaterialButton>(R.id.btn_confirm)
                buttonConfirm.setOnClickListener {
                    dialog.dismiss()
                }


//                val snackbar = Snackbar.make(it!!, Constants.listMessage.get(Random.nextInt(0, Constants.listMessage.size - 1)), Snackbar.LENGTH_INDEFINITE)
//
//                snackbar.setAction("OK", View.OnClickListener {
//                    snackbar.dismiss()
//                })
//
//                snackbar.show()
            }

            Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        when(p0?.id){
            R.id.edtStatus ->{
                status = p2 + 1
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

}