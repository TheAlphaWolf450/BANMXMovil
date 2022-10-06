package mx.itesm.banmxmovil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController

class TarjetasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tarjetas, container, false)

        view.findViewById<Button>(R.id.regresarBotonAddTarjeta).setOnClickListener {
            findNavController().navigate(R.id.action_pagoFragment_to_tarjetasFragment)
        }
        view.findViewById<Button>(R.id.anadirTarjetaAddTarjeta).setOnClickListener {
            findNavController().navigate(R.id.action_tarjetasFragment_to_agregarTarjetaPerfil)
        }
        view.findViewById<Button>(R.id.pagarAddTarjeta).setOnClickListener {
            findNavController().navigate(R.id.action_tarjetasFragment_to_agradecimientoFragment)
        }
        return view
    }
}