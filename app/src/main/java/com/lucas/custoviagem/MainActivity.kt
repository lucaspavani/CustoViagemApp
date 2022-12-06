package com.lucas.custoviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lucas.custoviagem.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*4. Criação do binding na linha 9, o lateinit é necessário pois o onCreate precisa criar a activity antes de instanciar o binding
          ActivityMainBinding é a classe criada para mapear os elementos
          Inflate permite interações entre a interface e a lógica do código
          O binding deve ser instanciado antes do setContentView, para que possa ser utilizado (linhas 13 e 14)

          5. Identificando o click no botão
          OnClickListener é uma interface que recebe um parâmetro de interface, deve se implementar a interface (linha7), dessa forma podemos usar o this
          como parâmetro, que será do tipo OnClickListener
          Em seguida é necessário implementar os membros*/

        binding.buttonCalculate.setOnClickListener(this)
        /*Quem vai responder pelo evento de click é a própria classe (this), no caso, a interface
          Para identificar que botão foi cliclado, serão utilizadas condições na função onClick (linha 35)
         */

    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_calculate) {
            calculate()
        }
    }

    private fun isValid(): Boolean {
        return (binding.editDistance.text.toString() != ""
                && binding.editPrice.text.toString() != ""
                && binding.editAutonomy.text.toString() != ""
                && binding.editAutonomy.text.toString().toFloat() != 0f)
    }

    private fun calculate() {

        if (isValid()) {
            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomy = binding.editAutonomy.text.toString().toFloat()

            val totalValue = (distance * price) / autonomy

            //Tratando a string para exibir com duas casas decimais e R$
            binding.textTotalValue.text = "R$ ${"%.2f".format(totalValue)}"
        } else {
            //Toast notification
            Toast.makeText(this, R.string.validate_fill_all_fields, Toast.LENGTH_SHORT).show()
        }

    }


}