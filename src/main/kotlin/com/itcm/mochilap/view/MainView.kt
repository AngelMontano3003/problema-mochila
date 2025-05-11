package com.itcm.mochilap.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itcm.mochilap.viewmodel.KnapsackViewModel

@Composable
fun MainView() {
    val viewModel = remember { KnapsackViewModel() }

    var name by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var value by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Título
        Text(
            text = "Problema de la mochila",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Formulario a rellenar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f).padding(end = 8.dp)) {
                TextField(value = name, onValueChange = { name = it }, label = { Text("Nombre") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = weight, onValueChange = { weight = it }, label = { Text("Peso") })
                Spacer(modifier = Modifier.height(8.dp))
                TextField(value = value, onValueChange = { value = it }, label = { Text("Valor") })
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    val w = weight.toIntOrNull()
                    val v = value.toIntOrNull()
                    if (name.isNotBlank() && w != null && v != null) {
                        viewModel.addItem(name, w, v)
                        name = ""
                        weight = ""
                        value = ""
                    }
                }) {
                    Text("Agregar elemento")
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    value = viewModel.maxWeight.value.toString(),
                    onValueChange = { viewModel.maxWeight.value = it.toIntOrNull() ?: 0 },
                    label = { Text("Capacidad máxima de la mochila") },
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = { viewModel.solveKnapsack() }) {
                    Text("Resolver mochila")

                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // Elementos agregados
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Elementos agregados:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn {
                    items(viewModel.items.size) { index ->
                        val item = viewModel.items[index]
                        item?.let {
                            Text("- ${it.name} (Peso: ${it.weight}, Valor: ${it.value})")
                        }
                    }
                }
            }

            // Solución
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Solución:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(8.dp))
                LazyColumn {
                    items(viewModel.result.size) { index ->
                        val item = viewModel.result[index]
                        item?.let {
                            Text("- ${it.name} (Peso: ${it.weight}, Valor: ${it.value})")
                        }
                    }
                }
            }
        }
    }
}
