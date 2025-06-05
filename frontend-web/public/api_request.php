<?php
// Captura os dados do comando do jogador
$requestPayload = file_get_contents("php://input");
$data = json_decode($requestPayload, true);

$comando = $data['comando'];
$resposta = "";

// Processa o comando
switch(strtolower($comando)) {
    case "help":
        $resposta = "Comandos disponíveis: HELP, CHECK [ITEM], USE [ITEM], GET [ITEM]";
        break;
    case "check barco":
        $resposta = "O barco está destruído e irreparável.";
        break;
    // Outros comandos aqui...
    default:
        $resposta = "Comando desconhecido.";
}

echo json_encode(['resposta' => $resposta]);
?>





