fun main() {
    var playerHealth = 100
    val playerAttack = 20
    val playerDefense = 16
    var bossHealth = 100

    println("===== GAME OF THE GAMES =====")
    println("""⣿⣿⡇⠄⣼⣿⣿⠿⣿⣿⣿⣦⠘⣿⣿⣿⣿⣿⠏⣰⣿⡿⠟⢻⣿⣿⣷⡀⠸⣿
⣿⣿⡇⠰⣿⣿⠁⠄⠄⠄⣿⣿⠆⢹⣿⣿⣿⣿⠄⣿⣿⠁⠄⠄⠄⣿⣿⡇⠄⣿
⣿⣿⡇⠄⢿⣿⣷⣤⣤⣼⣿⡟⢀⣿⣿⣿⣿⣿⡄⠻⣿⣷⣤⣤⣾⣿⡿⠁⠄⣿
⣿⣿⠃⢸⣦⡙⠛⠿⠟⠛⠉⣠⣾⣿⣿⣿⣿⣿⣿⣆⡈⠛⠻⠿⠛⢋⣴⡇⢸⣿
⣿⣿⡀⠈⢿⣿⣷⣶⣶⣶⣿⣿⣿⣿⠛⣿⡋⣿⣿⣿⣿⣷⣶⣶⣾⣿⡿⠄⢸⣿
⣿⣿⡇⠄⠈⢿⣿⣯⡻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⣽⣿⡟⠄⠄⣮⣿
⣿⣿⣷⠄⠄⠄⠹⣿⣷⣌⠙⢿⣿⣿⣿⣿⣿⣿⣿⡿⠟⢁⣾⣿⠋⠄⠄⠄⢹⣿""")

    println("\nOlá jogador, V0C4 1RÁ M0RR5R H0J3!!!!!!!!!!!!!")

    while (playerHealth > 0 && bossHealth > 0) {
        mostrarStatus(playerHealth, bossHealth)
        val escolha = escolherAcao()

        when (escolha) {
            1 -> {
                bossHealth = atacar(playerAttack, bossHealth)
                if (bossHealth > 0) {
                    playerHealth = bossContraAtaca(playerHealth)
                }
            }
            2 -> {
                playerHealth = defender(playerDefense, playerHealth)
            }
            3 -> {
                playerHealth = fugir(playerHealth)
            }
            else -> {
                println("Escolha uma função válida")
            }
        }
    }

    encerrarJogo(playerHealth, bossHealth)
}

// Funções auxiliares

fun mostrarStatus(playerHealth: Int, bossHealth: Int) {
    println("\n=== STATUS ===")
    println("Vida do jogador: $playerHealth")
    println("Vida do chefe: $bossHealth")
}

fun escolherAcao(): Int {
    println("\nEscolha sua ação:")
    println("1. Atacar")
    println("2. Defender")
    println("3. Fugir")
    print("Digite o número da sua ação: ")
    return readLine()?.toIntOrNull() ?: -1
}

fun atacar(playerAttack: Int, bossHealth: Int): Int {
    println("Você atacou o Boss causando $playerAttack de dano!")
    return bossHealth - playerAttack
}

fun defender(playerDefense: Int, playerHealth: Int): Int {
    val dano = 15 - playerDefense
    return if (dano > 0) {
        println("Você se defende! O boss ataca e causa $dano de dano.")
        playerHealth - dano
    } else {
        println("Você bloqueou totalmente o ataque do boss!")
        playerHealth
    }
}

fun fugir(playerHealth: Int): Int {
    println("Você tentou fugir... O boss te acertou uma PEDRADA de 10.000 de dano! 💀")
    return playerHealth - 10000
}

fun bossContraAtaca(playerHealth: Int): Int {
    val danoBoss = 15
    println("O boss contra-ataca e causa $danoBoss de dano!")
    return playerHealth - danoBoss
}

fun encerrarJogo(playerHealth: Int, bossHealth: Int) {
    if (playerHealth <= 0) {
        println("\n💀 Você morreu! Fim de jogo.")
    } else if (bossHealth <= 0) {
        println("\n🎉 Parabéns! Você derrotou o Boss!")
    }
}
