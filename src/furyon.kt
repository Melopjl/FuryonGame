import kotlin.random.Random

fun main() {
    // 1️⃣ Introdução do jogo
    println("===== GAME OF THE GAMES =====")
    println("""
        ⣿⣿⡇⠄⣼⣿⣿⠿⣿⣿⣿⣦⠘⣿⣿⣿⣿⣿⠏⣰⣿⡿⠟⢻⣿⣿⣷⡀⠸⣿
        ⣿⣿⡇⠰⣿⣿⠁⠄⠄⠄⣿⣿⠆⢹⣿⣿⣿⣿⠄⣿⣿⠁⠄⠄⠄⣿⣿⡇⠄⣿
        ⣿⣿⡇⠄⢿⣿⣷⣤⣤⣼⣿⡟⢀⣿⣿⣿⣿⣿⡄⠻⣿⣷⣤⣤⣾⣿⡿⠁⠄⣿
        ⣿⣿⠃⢸⣦⡙⠛⠿⠟⠛⠉⣠⣾⣿⣿⣿⣿⣿⣿⣆⡈⠛⠻⠿⠛⢋⣴⡇⢸⣿
        ⣿⣿⡀⠈⢿⣿⣷⣶⣶⣶⣿⣿⣿⣿⠛⣿⡋⣿⣿⣿⣿⣷⣶⣶⣾⣿⡿⠄⢸⣿
        ⣿⣿⡇⠄⠈⢿⣿⣯⡻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⣽⣿⡟⠄⠄⣮⣿
        ⣿⣿⣷⠄⠄⠄⠹⣿⣷⣌⠙⢿⣿⣿⣿⣿⣿⣿⣿⡿⠟⢁⣾⣿⠋⠄⠄⠄⢹⣿
    """)
    println("\nOlá jogador, prepare-se para a batalha!! 💀\n")

    // 2️⃣ Criação de personagens
    val player = Player("Herói", 100, 20, 16, 3)
    val boss = Boss("Chefão", 120, 18)

    var turno = 1

    // 3️⃣ Loop do jogo
    while (player.vida > 0 && boss.vida > 0) {
        println("\n=== TURNO $turno ===")
        mostrarStatus(player, boss)
        val acao = escolherAcao(player)

        when (acao) {
            1 -> player.atacar(boss)
            2 -> player.defender(boss)
            3 -> player.usarPocao()
            4 -> player.golpeEspecial(boss)
            else -> println("Escolha inválida! Perdeu a vez.")
        }

        if (boss.vida > 0) {
            boss.atacar(player)
        }

        turno++
    }

    encerrarJogo(player, boss)
}

// === CLASSES ===

open class Personagem(val nome: String, var vida: Int, val ataque: Int, val defesa: Int) {
    fun mostrarBarraVida() {
        val totalBlocos = 10
        val preenchido = (vida * totalBlocos / 100).coerceIn(0, totalBlocos)
        val barra = "█".repeat(preenchido) + " ".repeat(totalBlocos - preenchido)
        println("$nome: [$barra] $vida/100")
    }
}

class Player(nome: String, vida: Int, ataque: Int, defesa: Int, var pocao: Int) :
    Personagem(nome, vida, ataque, defesa) {

    fun atacar(boss: Boss) {
        val dano = ataque + Random.nextInt(-5, 6)
        println("$nome atacou ${boss.nome} causando $dano de dano! ⚔️")
        boss.vida -= dano
    }

    fun defender(boss: Boss) {
        val danoRecebido = (boss.ataque - defesa).coerceAtLeast(0)
        println("$nome se defendeu! Recebeu $danoRecebido de dano do ${boss.nome}. 🛡️")
        vida -= danoRecebido
    }

    fun usarPocao() {
        if (pocao > 0) {
            val cura = 30
            vida += cura
            if (vida > 100) vida = 100
            pocao--
            println("$nome usou uma poção e recuperou $cura de vida! Poções restantes: $pocao 🍎")
        } else {
            println("Sem poções restantes! 😢")
        }
    }

    fun golpeEspecial(boss: Boss) {
        val dano = ataque * 2 + Random.nextInt(0, 11)
        println("$nome usou um GOLPE ESPECIAL contra ${boss.nome} causando $dano de dano! 💥")
        boss.vida -= dano
    }
}

class Boss(nome: String, vida: Int, ataque: Int) : Personagem(nome, vida, ataque, 0) {
    fun atacar(player: Player) {
        val dano = ataque + Random.nextInt(-3, 4)
        println("$nome contra-ataca causando $dano de dano! 😈")
        player.vida -= dano
    }
}

// === FUNÇÕES AUXILIARES ===

fun mostrarStatus(player: Player, boss: Boss) {
    println()
    player.mostrarBarraVida()
    boss.mostrarBarraVida()
}

fun escolherAcao(player: Player): Int {
    println("\nEscolha sua ação:")
    println("1. Atacar ⚔️")
    println("2. Defender 🛡️")
    println("3. Usar poção (${player.pocao} restantes) 🍎")
    println("4. Golpe especial 💥")
    print("Digite o número da ação: ")
    return readLine()?.toIntOrNull() ?: -1
}

fun encerrarJogo(player: Player, boss: Boss) {
    println("\n=== FIM DE JOGO ===")
    when {
        player.vida <= 0 && boss.vida <= 0 -> println("Empate! Ambos caíram no campo de batalha. ⚔️💀")
        player.vida <= 0 -> println("💀 Você morreu! O Boss venceu! 😈")
        boss.vida <= 0 -> println("🎉 Parabéns! Você derrotou o Boss! 🏆")
    }
}
