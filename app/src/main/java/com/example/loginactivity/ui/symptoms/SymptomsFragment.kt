package com.example.loginactivity.ui.symptoms

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.loginactivity.R
import com.example.loginactivity.databinding.FragmentSymptomsBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_symptoms.*
import java.time.LocalDate


class SymptomsFragment : Fragment() {
    private val db = FirebaseFirestore.getInstance()
    private lateinit var symptomsViewModel: SymptomsViewModel
    private var _binding: FragmentSymptomsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        symptomsViewModel =
                ViewModelProvider(this).get(SymptomsViewModel::class.java)

        _binding = FragmentSymptomsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textSlideshow
        //slideshowViewModel.text.observe(viewLifecycleOwner, Observer {
        //    textView.text = it
        //})
        return root
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        val prefs = this.activity?.getSharedPreferences(
            getString(R.string.prefs_file),
            Context.MODE_PRIVATE
        )

        symptomsViewModel.text.observe(viewLifecycleOwner, Observer {

            var listenDiarrea: Int = 0
            Diarrea.setOnClickListener {
                selDiarrea.visibility = View.VISIBLE
                listenDiarrea = 1
            }
            selDiarrea.setOnClickListener {
                selDiarrea.visibility = View.GONE
                listenDiarrea = 0
            }

            var listenDificultad_Respiratoria: Int = 0
            Dificultad_Respiratoria.setOnClickListener {
                selDificultad_Respiratoria.visibility = View.VISIBLE
                listenDificultad_Respiratoria = 1
            }
            selDificultad_Respiratoria.setOnClickListener {
                selDificultad_Respiratoria.visibility = View.GONE
                listenDificultad_Respiratoria = 0
            }

            var listenDificultad_Respiratoria_n: Int = 0
            Dificultad_Respiratoria_n.setOnClickListener {
                selDificultad_Respiratoria_n.visibility = View.VISIBLE
                listenDificultad_Respiratoria_n = 1
            }
            selDificultad_Respiratoria_n.setOnClickListener {
                selDificultad_Respiratoria_n.visibility = View.GONE
                listenDificultad_Respiratoria_n = 0
            }

            var listenDolor_Abdomen_corto: Int = 0
            Dolor_Abdomen_corto.setOnClickListener {
                selDolor_Abdomen_corto.visibility = View.VISIBLE
                listenDolor_Abdomen_corto = 1
            }
            selDolor_Abdomen_corto.setOnClickListener {
                selDolor_Abdomen_corto.visibility = View.GONE
                listenDolor_Abdomen_corto = 0
            }

            var listenDolor_Abdomen_largo: Int = 0
            Dolor_Abdomen_largo.setOnClickListener {
                selDolor_Abdomen_largo.visibility = View.VISIBLE
                listenDolor_Abdomen_largo = 1
            }
            selDolor_Abdomen_largo.setOnClickListener {
                selDolor_Abdomen_largo.visibility = View.GONE
                listenDolor_Abdomen_largo = 0
            }

            var listenDolor_en_Pecho_n: Int = 0
            Dolor_en_Pecho_n.setOnClickListener {
                selDolor_en_Pecho_n.visibility = View.VISIBLE
                listenDolor_en_Pecho_n = 1
            }
            selDolor_en_Pecho_n.setOnClickListener {
                selDolor_en_Pecho_n.visibility = View.GONE
                listenDolor_en_Pecho_n = 0
            }

            var listenDolor_en_Pecho_ag: Int = 0
            Dolor_en_Pecho_ag.setOnClickListener {
                selDolor_en_Pecho_ag.visibility = View.VISIBLE
                listenDolor_en_Pecho_ag = 1
            }
            selDolor_en_Pecho_ag.setOnClickListener {
                selDolor_en_Pecho_ag.visibility = View.GONE
                listenDolor_en_Pecho_ag = 0
            }

            var listenDolor_en_Pecho_cr: Int = 0
            Dolor_en_Pecho_cr.setOnClickListener {
                selDolor_en_Pecho_cr.visibility = View.VISIBLE
                listenDolor_en_Pecho_cr = 1
            }
            selDolor_en_Pecho_cr.setOnClickListener {
                selDolor_en_Pecho_cr.visibility = View.GONE
                listenDolor_en_Pecho_cr = 0
            }

            var listenDolor_en_parte_baja_esp: Int = 0
            Dolor_en_parte_baja_esp.setOnClickListener {
                selDolor_en_parte_baja_esp.visibility = View.VISIBLE
                listenDolor_en_parte_baja_esp = 1
            }
            selDolor_en_parte_baja_esp.setOnClickListener {
                selDolor_en_parte_baja_esp.visibility = View.GONE
                listenDolor_en_parte_baja_esp = 0
            }

            var listenDolores_de_cabeza: Int = 0
            Dolores_de_cabeza.setOnClickListener {
                selDolores_de_cabeza.visibility = View.VISIBLE
                listenDolores_de_cabeza = 1
            }
            selDolores_de_cabeza.setOnClickListener {
                selDolores_de_cabeza.visibility = View.GONE
                listenDolores_de_cabeza = 0
            }

            var listenErupciones_cutaneas: Int = 0
            Erupciones_cutaneas.setOnClickListener {
                selErupciones_cutaneas.visibility = View.VISIBLE
                listenErupciones_cutaneas = 1
            }
            selErupciones_cutaneas.setOnClickListener {
                selErupciones_cutaneas.visibility = View.GONE
                listenErupciones_cutaneas = 0
            }

            var listenFiebre: Int = 0
            Fiebre.setOnClickListener {
                selFiebre.visibility = View.VISIBLE
                listenFiebre = 1
            }
            selFiebre.setOnClickListener {
                selFiebre.visibility = View.GONE
                listenFiebre = 0
            }

            var listenFiebre_en_lactantes: Int = 0
            Fiebre_en_lactantes.setOnClickListener {
                selFiebre_en_lactantes.visibility = View.VISIBLE
                listenFiebre_en_lactantes = 1
            }
            selFiebre_en_lactantes.setOnClickListener {
                selFiebre_en_lactantes.visibility = View.GONE
                listenFiebre_en_lactantes = 0
            }

            var listenHinchazon_de_la_cara: Int = 0
            Hinchazon_de_la_cara.setOnClickListener {
                selHinchazon_de_la_cara.visibility = View.VISIBLE
                listenHinchazon_de_la_cara = 1
            }
            selHinchazon_de_la_cara.setOnClickListener {
                selHinchazon_de_la_cara.visibility = View.GONE
                listenHinchazon_de_la_cara = 0
            }

            var listenHinchazon_del_cuello: Int = 0
            Hinchazon_del_cuello.setOnClickListener {
                selHinchazon_del_cuello.visibility = View.VISIBLE
                listenHinchazon_del_cuello = 1
            }
            selHinchazon_del_cuello.setOnClickListener {
                selHinchazon_del_cuello.visibility = View.GONE
                listenHinchazon_del_cuello = 0
            }

            var listenNauseas_y_vomito: Int = 0
            Nauseas_y_vomito.setOnClickListener {
                selNauseas_y_vomito.visibility = View.VISIBLE
                listenNauseas_y_vomito = 1
            }
            selNauseas_y_vomito.setOnClickListener {
                selNauseas_y_vomito.visibility = View.GONE
                listenNauseas_y_vomito = 0
            }

            var listenNauseas_y_vomito_n: Int = 0
            Nauseas_y_vomito_n.setOnClickListener {
                selNauseas_y_vomito_n.visibility = View.VISIBLE
                listenNauseas_y_vomito_n = 1
            }
            selNauseas_y_vomito_n.setOnClickListener {
                selNauseas_y_vomito_n.visibility = View.GONE
                listenNauseas_y_vomito_n = 0
            }

            var listenPerdida_del_cabello: Int = 0
            Perdida_del_cabello.setOnClickListener {
                selPerdida_del_cabello.visibility = View.VISIBLE
                listenPerdida_del_cabello = 1
            }
            selPerdida_del_cabello.setOnClickListener {
                selPerdida_del_cabello.visibility = View.GONE
                listenPerdida_del_cabello = 0
            }

            var listenProblemas_al_orinar: Int = 0
            Problemas_al_orinar.setOnClickListener {
                selProblemas_al_orinar.visibility = View.VISIBLE
                listenProblemas_al_orinar = 1
            }
            selProblemas_al_orinar.setOnClickListener {
                selProblemas_al_orinar.visibility = View.GONE
                listenProblemas_al_orinar = 0
            }

            var listenProblemas_de_audicion: Int = 0
            Problemas_de_audicion.setOnClickListener {
                selProblemas_de_audicion.visibility = View.VISIBLE
                listenProblemas_de_audicion = 1
            }
            selProblemas_de_audicion.setOnClickListener {
                selProblemas_de_audicion.visibility = View.GONE
                listenProblemas_de_audicion = 0
            }

            var listenProblemas_de_eliminacion: Int = 0
            Problemas_de_eliminacion.setOnClickListener {
                selProblemas_de_eliminacion.visibility = View.VISIBLE
                listenProblemas_de_eliminacion = 1
            }
            selProblemas_de_eliminacion.setOnClickListener {
                selProblemas_de_eliminacion.visibility = View.GONE
                listenProblemas_de_eliminacion = 0
            }

            var listenProblemas_de_eliminacion_n: Int = 0
            Problemas_de_eliminacion_n.setOnClickListener {
                selProblemas_de_eliminacion_n.visibility = View.VISIBLE
                listenProblemas_de_eliminacion_n = 1
            }
            selProblemas_de_eliminacion_n.setOnClickListener {
                selProblemas_de_eliminacion_n.visibility = View.GONE
                listenProblemas_de_eliminacion_n = 0
            }

            var listenProblemas_de_la_boca: Int = 0
            Problemas_de_la_boca.setOnClickListener {
                selProblemas_de_la_boca.visibility = View.VISIBLE
                listenProblemas_de_la_boca = 1
            }
            selProblemas_de_la_boca.setOnClickListener {
                selProblemas_de_la_boca.visibility = View.GONE
                listenProblemas_de_la_boca = 0
            }

            var listenProblemas_de_la_boca_en_n: Int = 0
            Problemas_de_la_boca_en_n.setOnClickListener {
                selProblemas_de_la_boca_en_n.visibility = View.VISIBLE
                listenProblemas_de_la_boca_en_n = 1
            }
            selProblemas_de_la_boca_en_n.setOnClickListener {
                selProblemas_de_la_boca_en_n.visibility = View.GONE
                listenProblemas_de_la_boca_en_n = 0
            }

            var listenProblemas_de_la_cadera: Int = 0
            Problemas_de_la_cadera.setOnClickListener {
                selProblemas_de_la_cadera.visibility = View.VISIBLE
                listenProblemas_de_la_cadera = 1
            }
            selProblemas_de_la_cadera.setOnClickListener {
                selProblemas_de_la_cadera.visibility = View.GONE
                listenProblemas_de_la_cadera = 0
            }

            var listenProblemas_de_garganta: Int = 0
            Problemas_de_garganta.setOnClickListener {
                selProblemas_de_garganta.visibility = View.VISIBLE
                listenProblemas_de_garganta = 1
            }
            selProblemas_de_garganta.setOnClickListener {
                selProblemas_de_garganta.visibility = View.GONE
                listenProblemas_de_garganta = 0
            }

            var listenProblemas_de_mano: Int = 0
            Problemas_de_mano.setOnClickListener {
                selProblemas_de_mano.visibility = View.VISIBLE
                listenProblemas_de_mano = 1
            }
            selProblemas_de_mano.setOnClickListener {
                selProblemas_de_mano.visibility = View.GONE
                listenProblemas_de_mano = 0
            }

            var listenProblemas_de_piernas: Int = 0
            Problemas_de_piernas.setOnClickListener {
                selProblemas_de_piernas.visibility = View.VISIBLE
                listenProblemas_de_piernas = 1
            }
            selProblemas_de_piernas.setOnClickListener {
                selProblemas_de_piernas.visibility = View.GONE
                listenProblemas_de_piernas = 0
            }

            var listenProblemas_de_los_dientes: Int = 0
            Problemas_de_los_dientes.setOnClickListener {
                selProblemas_de_los_dientes.visibility = View.VISIBLE
                listenProblemas_de_los_dientes = 1
            }
            selProblemas_de_los_dientes.setOnClickListener {
                selProblemas_de_los_dientes.visibility = View.GONE
                listenProblemas_de_los_dientes = 0
            }

            var listenProblemas_de_los_genitales_m: Int = 0
            Problemas_de_los_genitales_m.setOnClickListener {
                selProblemas_de_los_genitales_m.visibility = View.VISIBLE
                listenProblemas_de_los_genitales_m = 1
            }
            selProblemas_de_los_genitales_m.setOnClickListener {
                selProblemas_de_los_genitales_m.visibility = View.GONE
                listenProblemas_de_los_genitales_m = 0
            }

            var listenProblemas_de_los_genitales_h: Int = 0
            Problemas_de_los_genitales_h.setOnClickListener {
                selProblemas_de_los_genitales_h.visibility = View.VISIBLE
                listenProblemas_de_los_genitales_h = 1
            }
            selProblemas_de_los_genitales_h.setOnClickListener {
                selProblemas_de_los_genitales_h.visibility = View.GONE
                listenProblemas_de_los_genitales_h = 0
            }

            var listenProblemas_de_los_ojos: Int = 0
            Problemas_de_los_ojos.setOnClickListener {
                selProblemas_de_los_ojos.visibility = View.VISIBLE
                listenProblemas_de_los_ojos = 1
            }
            selProblemas_de_los_ojos.setOnClickListener {
                selProblemas_de_los_ojos.visibility = View.GONE
                listenProblemas_de_los_ojos = 0
            }

            var listenProblemas_de_los_pies: Int = 0
            Problemas_de_los_pies.setOnClickListener {
                selProblemas_de_los_pies.visibility = View.VISIBLE
                listenProblemas_de_los_pies = 1
            }
            selProblemas_de_los_pies.setOnClickListener {
                selProblemas_de_los_pies.visibility = View.GONE
                listenProblemas_de_los_pies = 0
            }

            var listenProblemas_de_los_tobillos: Int = 0
            Problemas_de_los_tobillos.setOnClickListener {
                selProblemas_de_los_tobillos.visibility = View.VISIBLE
                listenProblemas_de_los_tobillos = 1
            }
            selProblemas_de_los_tobillos.setOnClickListener {
                selProblemas_de_los_tobillos.visibility = View.GONE
                listenProblemas_de_los_tobillos = 0
            }

            var listenProblemas_de_oido: Int = 0
            Problemas_de_oido.setOnClickListener {
                selProblemas_de_oido.visibility = View.VISIBLE
                listenProblemas_de_oido = 1
            }
            selProblemas_de_oido.setOnClickListener {
                selProblemas_de_oido.visibility = View.GONE
                listenProblemas_de_oido = 0
            }

            var listenProblemas_de_rodilla: Int = 0
            Problemas_de_rodilla.setOnClickListener {
                selProblemas_de_rodilla.visibility = View.VISIBLE
                listenProblemas_de_rodilla = 1
            }
            selProblemas_de_rodilla.setOnClickListener {
                selProblemas_de_rodilla.visibility = View.GONE
                listenProblemas_de_rodilla = 0
            }

            var listenProblemas_de_ciclo_m: Int = 0
            Problemas_de_ciclo_m.setOnClickListener {
                selProblemas_de_ciclo_m.visibility = View.VISIBLE
                listenProblemas_de_ciclo_m = 1
            }
            selProblemas_de_ciclo_m.setOnClickListener {
                selProblemas_de_ciclo_m.visibility = View.GONE
                listenProblemas_de_ciclo_m = 0
            }

            var listenProblemas_del_hombro: Int = 0
            Problemas_del_hombro.setOnClickListener {
                selProblemas_del_hombro.visibility = View.VISIBLE
                listenProblemas_del_hombro = 1
            }
            selProblemas_del_hombro.setOnClickListener {
                selProblemas_del_hombro.visibility = View.GONE
                listenProblemas_del_hombro = 0
            }

            var listenAlimentacion_n: Int = 0
            Alimentacion_n.setOnClickListener {
                selAlimentacion_n.visibility = View.VISIBLE
                listenAlimentacion_n = 1
            }
            selAlimentacion_n.setOnClickListener {
                selAlimentacion_n.visibility = View.GONE
                listenAlimentacion_n = 0
            }

            var listenTejido_m_hom: Int = 0
            Tejido_m_hom.setOnClickListener {
                selTejido_m_hom.visibility = View.VISIBLE
                listenTejido_m_hom = 1
            }
            selTejido_m_hom.setOnClickListener {
                selTejido_m_hom.visibility = View.GONE
                listenTejido_m_hom = 0
            }

            var listenProblema_senos: Int = 0
            Problema_senos.setOnClickListener {
                selProblema_senos.visibility = View.VISIBLE
                listenProblema_senos = 1
            }
            selProblema_senos.setOnClickListener {
                selProblema_senos.visibility = View.GONE
                listenProblema_senos = 0
            }

            var listenResfriados_gripe: Int = 0
            Resfriados_gripe.setOnClickListener {
                selResfriados_gripe.visibility = View.VISIBLE
                listenResfriados_gripe = 1
            }
            selResfriados_gripe.setOnClickListener {
                selResfriados_gripe.visibility = View.GONE
                listenResfriados_gripe = 0
            }

            var listenTos: Int = 0
            Tos.setOnClickListener {
                selTos.visibility = View.VISIBLE
                listenTos = 1
            }
            selTos.setOnClickListener {
                selTos.visibility = View.GONE
                listenTos = 0
            }

            diagbuttonDataView.setOnClickListener {
                val sumaSintoma =
                    listenAlimentacion_n + listenDiarrea + listenDificultad_Respiratoria + listenDificultad_Respiratoria_n +listenDolor_Abdomen_corto
                + listenDolor_Abdomen_largo + listenDolor_en_Pecho_ag + listenDolor_en_Pecho_cr + listenDolor_en_Pecho_n +listenDolor_en_parte_baja_esp
                + listenDolores_de_cabeza + listenErupciones_cutaneas + listenFiebre + listenFiebre_en_lactantes + listenHinchazon_de_la_cara
                + listenHinchazon_del_cuello + listenNauseas_y_vomito + listenNauseas_y_vomito_n + listenPerdida_del_cabello + listenProblema_senos
                + listenProblemas_al_orinar + listenProblemas_de_audicion +listenProblemas_de_ciclo_m + listenProblemas_de_eliminacion + listenProblemas_de_eliminacion_n
                + listenProblemas_de_garganta + listenProblemas_de_la_boca +listenProblemas_de_la_boca_en_n + listenProblemas_de_la_cadera + listenProblemas_de_los_dientes
                + listenProblemas_de_los_genitales_h + listenProblemas_de_los_genitales_m +listenProblemas_de_los_ojos + listenProblemas_de_los_pies + listenProblemas_de_los_tobillos
                + listenProblemas_de_mano + listenProblemas_de_oido + listenProblemas_de_piernas + listenProblemas_de_rodilla + listenProblemas_del_hombro
                + listenResfriados_gripe + listenTejido_m_hom + listenTos
                Log.d("SUMASINTOMA", "----------------------------->\n$sumaSintoma\n" +
                        "alimentacionnin:$listenAlimentacion_n\n diarrea:$listenDiarrea\n dificultadres:$listenDificultad_Respiratoria\n dificultadresn:$listenDificultad_Respiratoria_n\n" +
                        "dolabdomencorto:$listenDolor_Abdomen_corto\n dolabdomenlargo:$listenDolor_Abdomen_largo\n dolpechoag:$listenDolor_en_Pecho_ag\n dolpechocr:$listenDolor_en_Pecho_cr\n dolpechon:$listenDolor_en_Pecho_n\n" +
                        "dolpartebajaesp:$listenDolor_en_parte_baja_esp\n dolcabeza:$listenDolores_de_cabeza\n erupcionescut:$listenErupciones_cutaneas\n fiebre:$listenFiebre\n fiebrenin:$listenFiebre_en_lactantes\n hinchazoncara:$listenHinchazon_de_la_cara\n" +
                        "hinchazoncuello:$listenHinchazon_del_cuello\n nauseasyvomito:$listenNauseas_y_vomito\n nauseasyvomitonin:$listenNauseas_y_vomito_n\n perdidacabello:$listenPerdida_del_cabello\n problemasenos:$listenProblema_senos\n problemaorinar:$listenProblemas_al_orinar\n problemaaudio:$listenProblemas_de_audicion\n" +
                        "problemaciclo:$listenProblemas_de_ciclo_m\n problemaeliminacion:$listenProblemas_de_eliminacion\n eliminacionnin:$listenProblemas_de_eliminacion_n\n garganta:$listenProblemas_de_garganta\n probboca:$listenProblemas_de_la_boca\n" +
                        "probbocanin:$listenProblemas_de_la_boca_en_n\n probcadera:$listenProblemas_de_la_cadera\n probdientes$listenProblemas_de_los_dientes\n genhombre:$listenProblemas_de_los_genitales_h\n genmujer:$listenProblemas_de_los_genitales_m\n" +
                        "problemaojos:$listenProblemas_de_los_ojos\n probpies:$listenProblemas_de_los_pies\n probtobillos:$listenProblemas_de_los_tobillos\n probmano:$listenProblemas_de_mano\n proboido:$listenProblemas_de_oido\n" +
                        "probpiernas:$listenProblemas_de_piernas\n probrodilla:$listenProblemas_de_rodilla\n probhombro:$listenProblemas_del_hombro\n refgripe:$listenResfriados_gripe\n tejidohombre:$listenTejido_m_hom\n tos:$listenTos\n")
                if (sumaSintoma > 1) {
                    val diagnosis = whatdiagnose(listenAlimentacion_n,listenDiarrea,listenDificultad_Respiratoria,listenDificultad_Respiratoria_n
                            ,listenDolor_Abdomen_corto,listenDolor_Abdomen_largo,listenDolor_en_Pecho_ag,listenDolor_en_Pecho_cr,listenDolor_en_Pecho_n
                            ,listenDolor_en_parte_baja_esp,listenDolores_de_cabeza,listenErupciones_cutaneas,listenFiebre,listenFiebre_en_lactantes,listenHinchazon_de_la_cara
                            ,listenHinchazon_del_cuello,listenNauseas_y_vomito,listenNauseas_y_vomito_n,listenPerdida_del_cabello,listenProblema_senos,listenProblemas_al_orinar,listenProblemas_de_audicion
                            ,listenProblemas_de_ciclo_m,listenProblemas_de_eliminacion,listenProblemas_de_eliminacion_n,listenProblemas_de_garganta,listenProblemas_de_la_boca
                            ,listenProblemas_de_la_boca_en_n,listenProblemas_de_la_cadera,listenProblemas_de_los_dientes,listenProblemas_de_los_genitales_h,listenProblemas_de_los_genitales_m
                            ,listenProblemas_de_los_ojos,listenProblemas_de_los_pies,listenProblemas_de_los_tobillos,listenProblemas_de_mano,listenProblemas_de_oido
                            ,listenProblemas_de_piernas,listenProblemas_de_rodilla,listenProblemas_del_hombro,listenResfriados_gripe,listenTejido_m_hom,listenTos)
                    if (!diagnosis.contentEquals(arrayOf("","","",""))){
                        val prefget = activity?.getSharedPreferences(
                            getString(R.string.prefs_file),
                            Context.MODE_PRIVATE
                        )
                        var title:String = ""
                        var titletext:String = ""
                        var subtitle:String = ""
                        var subtitletext:String = ""
                        for(element in diagnosis){
                            title = diagnosis[0]
                            titletext = diagnosis[1]
                            subtitle = diagnosis[2]
                            subtitletext = diagnosis[3]
                        }
                        val email = prefget?.getString("email", null)
                        val today = LocalDate.now().toString()
                        if (email != null) {
                            db.collection("users").document(email).update(
                                hashMapOf(
                                    "history" to
                                            hashMapOf(
                                                today to hashMapOf(
                                                    title to titletext,
                                                    subtitle to subtitletext
                                                )
                                            )
                                ) as Map<String, Any>
                            )
                        }
                    }
                } else {
                    val lang : String = resources.getString(R.string.langua)
                    if (lang == "es"){
                        Toast.makeText(activity, "Debe de elegir por lo menos 2 sintomas para poder hacer un diagnostico", Toast.LENGTH_SHORT).show()
                    }
                    if (lang == "en"){
                        Toast.makeText(activity, "You must choose at least 2 symptoms in order to make a diagnosis", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
    }
    //42 data for fun whatdiagnose
    fun whatdiagnose(alin:Int,diar:Int,dres:Int,resn:Int,dabc:Int,
                     dabl:Int,depa:Int,depc:Int,depn:Int,depb:Int,
                     ddca:Int,ercu:Int,fieb:Int,fiel:Int,hdlc:Int,
                     hdec:Int,nayv:Int,nayn:Int,pedc:Int,prse:Int,
                     prao:Int,prda:Int,prdc:Int,prde:Int,prdn:Int,
                     prdg:Int,pdlb:Int,pdln:Int,pdlc:Int,pdld:Int,
                     pdlh:Int,pdlm:Int,pdlo:Int,pdlp:Int,pdlt:Int,
                     prdm:Int,prdo:Int,prdp:Int,prdr:Int,prdh:Int,
                     regr:Int,temh:Int,toss:Int): Array<String>
    {
        var some = arrayOf<String>("","","","")

        if (diar==1 && nayv==1 && dabc==1){
            some = arrayOf("DIAGNOSTICO",
                "Usted podría tener una forma de DIARREA BACTERIANA (por bacterias) o por causa de infección por un parásito llamado GIARDIA.",
                "AUTOCUIDADO",
                "Llame a su médico pronto. Asegúrese de tomar líquidos en abundancia para prevenir la deshidratación. Evite la cafeína.")
        }

        return some
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
