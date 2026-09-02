# Hogwarts Quiz — Introdução ao Desenvolvimento Android

Projeto-âncora de um **mini-curso de introdução ao desenvolvimento Android** (2 dias, 1h por aula).
O app é um quiz: aparece um personagem de Harry Potter e você adivinha a casa de Hogwarts dele.
Os dados de cada rodada vêm de uma API pública, e no fim há uma tela de resultado com o placar.

O repositório contém **duas coisas**:

| | |
|---|---|
| 📱 **O app** | `app/` — o Hogwarts Quiz em Kotlin + Jetpack Compose |
| 🎞️ **Os slides do curso** | `curso-android-slides.html` (abre no navegador) e `curso-android-slides.pdf` |

---

## O app

### Como funciona

1. **Tela do quiz** — carrega um personagem aleatório da API, mostra a foto e o nome, e oferece as 4 casas como botões.
2. Você escolhe uma casa → o app confere com a casa real do personagem e soma no placar.
3. São **5 rodadas**. Ao final, navega para a **tela de resultado** (`acertos / total`).
4. O botão "Jogar novamente" reinicia o quiz.

### Stack

- **Kotlin** 2.2
- **Jetpack Compose** (Material 3) — UI declarativa
- **Navigation Compose** — navegação entre as duas telas
- **Retrofit + Gson** — chamadas HTTP e conversão de JSON
- **Coil** — carregamento das imagens dos personagens por URL
- **Coroutines** (`suspend` + `LaunchedEffect`) — rede sem travar a interface

### API

[potterapi-fedeperin](https://potterapi-fedeperin.vercel.app/) — API pública e gratuita de Harry Potter.
Endpoint usado: `GET /pt/characters?index={n}` (personagens em português).

### Estrutura

```
app/src/main/java/com/example/harry_potter_quiz/
├── MainActivity.kt          # Activity + NavHost (rotas "quiz" e "result/...")
├── screen/
│   ├── QuizContainer.kt      # tela do quiz: estado, rodadas, placar, chamada à API
│   └── ResultScreen.kt       # tela de resultado
├── model/
│   └── Personagem.kt         # data class do personagem + mapeamento para a casa (enum House)
├── helper/
│   ├── ApiService.kt         # contrato Retrofit
│   └── RetroFitInstance.kt   # configuração do Retrofit (base URL, converter)
└── ui/theme/                 # cores, tipografia e tema do Material 3
```

### Rodando o projeto

**Pré-requisitos:** Android Studio (versão recente), JDK 11+, um emulador ou dispositivo com **Android 7.0 (API 24)** ou superior.

```bash
git clone git@github.com:LeoOmori/curso-android.git
cd curso-android
./gradlew installDebug        # instala no dispositivo/emulador conectado
# ou abra a pasta no Android Studio e clique em Run ▶
```

O app precisa de conexão com a internet (permissão `INTERNET` já declarada no manifesto).

---

## O curso

Material para apresentar em sala. Slides em `curso-android-slides.html` — abra no navegador; setas / espaço para navegar, tecla `E` para editar os textos direto na página.

### Dia 1 — Montando a tela

Como o Android funciona · o que é um app (Activity, Android Studio, Gradle) · Kotlin essencial em um slide · Jetpack Compose e UI declarativa · a função `@Composable` e os blocos (`Column`, `Row`, `Box`, `Text`, `Button`…) · `Modifier` · tema e cores · `@Preview`.

> **Objetivo:** ao final do Dia 1, montar qualquer tela estática do app.

### Dia 2 — Dando vida à tela

Estado e recomposição (`remember` / `mutableStateOf`) · reagir a cliques · buscar dados da internet (API REST, JSON, Retrofit, Coil) · navegação entre telas · e então montar o Hogwarts Quiz inteiro, de ponta a ponta.

> **Objetivo:** conectar todas as peças e ter o quiz funcionando.

O código do app é escrito **ao vivo, depois da apresentação** — os slides trazem só os conceitos.

---

## Ideias para evoluir

- Guardar dados no celular com **Room** e **SQLite** (recorde, histórico).
- Tela de **carregando** e aviso visível de erro de rede.
- **Animações** de transição entre as rodadas.
- Fazer o quiz com **outros valores**: apelido, dia de nascimento, ator, etc.

## Recursos

- [Android Developers — Cursos](https://developer.android.com/courses)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Documentação do Kotlin](https://kotlinlang.org/docs)
- [Now in Android (app de referência)](https://github.com/android/nowinandroid)
