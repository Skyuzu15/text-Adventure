<?php
session_start();

// Limpa os dados de sessão para iniciar um novo jogo
if (isset($_POST['new_game'])) {
    session_destroy();
    header("Location: game.php");
    exit;
}

// Carrega o jogo salvo, se existir
if (isset($_POST['continue_game'])) {
    if (isset($_SESSION['save'])) {
        header("Location: game.php");
        exit;
    } else {
        $error_message = "Nenhum jogo salvo encontrado.";
    }
}
?>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>O Mistério da Ilha Esquecida</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-image: url('https://img.freepik.com/fotos-premium/uma-ilha-misteriosa-com-ruinas-e-segredos-ia-geradora-de-fundo-panoramico_918839-3918.jpg?w=1380'); /* Substitua pelo URL da imagem */
            background-size: cover;
            color: #fff;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            text-align: center;
            background-color: rgba(0, 0, 0, 0.7);
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 30px rgba(0, 0, 0, 0.5);
        }
        h1 {
            color: #f8f9fa;
            font-size: 2.5em;
            margin-bottom: 20px;
        }
        p {
            font-size: 1.2em;
            color: #ccc;
            margin-bottom: 40px;
        }
        form {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 15px 30px;
            font-size: 1.2em;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
        }
        button:hover {
            background-color: #0056b3;
        }
        .error {
            color: #ff5555;
            margin-top: 10px;
        }
        @media (max-width: 600px) {
            h1 {
                font-size: 2em;
            }
            button {
                padding: 10px 20px;
                font-size: 1em;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>O Mistério da Ilha Esquecida</h1>
        <p>Você está prestes a embarcar em uma jornada cheia de enigmas e desafios. Desvende os segredos da ilha e encontre o caminho para a liberdade.</p>
        <form method="post">
            <button type="submit" name="new_game">Iniciar Nova Aventura</button>
            <button type="submit" name="continue_game">Continuar Jogo Salvo</button>
        </form>
        <?php if (isset($error_message)): ?>
            <div class="error"><?= $error_message ?></div>
        <?php endif; ?>
    </div>
</body>
</html>
