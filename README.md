# Clima Agora 🌤️

## Descrição
Aplicativo Android nativo que consome uma API pública para consultar e exibir informações climáticas em tempo real de uma cidade informada pelo usuário. O objetivo é facilitar a visualização do clima atual de forma rápida e com uma interface limpa.

## API utilizada
- **Nome da API:** wttr.in
- **Endpoint utilizado:** `/{cidade}?format=j1`
- **Exemplo de URL consultada:** `https://wttr.in/Itaperuna%20rj?format=j1`
- **Principais dados retornados:** Temperatura atual (°C), Umidade (%) e Velocidade do Vento (km/h).

## Funcionalidades
- Entrada de dados pelo usuário (nome da cidade)
- Validação de campo vazio
- Consulta a uma API pública
- Exibição dos dados retornados na interface gráfica
- Tratamento básico de erro (mensagem amigável se a cidade não for encontrada ou sem internet)

## Tecnologias utilizadas
- Kotlin
- Android Studio
- XML
- Biblioteca de requisição Volley
- API pública wttr.in

## Permissões utilizadas
O aplicativo utiliza a permissão INTERNET para realizar requisições à API pública.

```xml
<uses-permission android:name="android.permission.INTERNET" />
