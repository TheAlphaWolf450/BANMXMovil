package mx.itesm.banmxmovil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class AgregarTarFragment : Fragment() {
    private lateinit var _nombreInputTarjeta: EditText
    private lateinit var _numTarjetaInputTarjeta: EditText
    private lateinit var _expInputTarjeta: EditText
    private lateinit var _codigoInputTarjeta: EditText

    val db = Firebase.firestore
    val args: AgregarTarFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_agregar_tar, container, false)

        _nombreInputTarjeta = view.findViewById(R.id.nombreInputTar)
        _numTarjetaInputTarjeta = view.findViewById(R.id.numTarjetaInputTar)
        _expInputTarjeta = view.findViewById(R.id.expInputTar)
        _codigoInputTarjeta = view.findViewById(R.id.codigoInputTar)

        view.findViewById<Button>(R.id.guardarBotonTar).setOnClickListener {
            val data = hashMapOf(
                "nombre" to _nombreInputTarjeta.text.toString(),
                "numTarjeta" to _numTarjetaInputTarjeta.text.toString(),
                "expTarjeta" to _expInputTarjeta.text.toString(),
                "codigoTarjeta" to _codigoInputTarjeta.text.toString()
            )

            // verificamos usuario
            if(Firebase.auth.currentUser == null) {

                // SIGNIFICA QUE HAY NECESIDAD DE RE-VALIDAR EL USUARIO
                // podrías redireccionar / terminar esta actividad
                Toast.makeText(context, "REVALIDA!", Toast.LENGTH_SHORT).show()
                requireActivity().finish()
            }

            //view.findViewById<TextView>(R.id.nombreViewPerfil).text = args.nombrePerfil
            //view.findViewById<TextView>(R.id.correoViewPerfil).text = args.emailPerfil
            db.collection("usuarios/${args.idUsuario}/tarjetas")
                .document(_numTarjetaInputTarjeta.text.toString()).set(data)

            //findNavController().navigate(R.id.action_agregarTarjetaPerfil_to_misTarjetasPerfilFragment)
            val action = AgregarTarFragmentDirections
                .actionAgregarTarFragmentToTarjetasFragment(
                    args.idUsuario
                )
            Toast.makeText(context, "Información Guardada", Toast.LENGTH_SHORT).show()
            findNavController().navigate(action)
        }
        view.findViewById<ImageButton>(R.id.regresarBotonTar).setOnClickListener {
            val action = AgregarTarFragmentDirections
                .actionAgregarTarFragmentToTarjetasFragment(
                    args.idUsuario
                )
            findNavController().navigate(action)


        }
        return view
    }
}