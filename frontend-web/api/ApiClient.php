<?php
class ApiClient {
    private $baseUrl;

    public function __construct($baseUrl) {
        $this->baseUrl = rtrim($baseUrl, '/');
    }

    public function callApi($endpoint, $method = 'GET', $data = null) {
        $url = $this->baseUrl . '/' . $endpoint;
        $curl = curl_init($url);

        curl_setopt($curl, CURLOPT_RETURNTRANSFER, true); 
        curl_setopt($curl, CURLOPT_CUSTOMREQUEST, $method); 

        if ($data) {
            curl_setopt($curl, CURLOPT_POSTFIELDS, json_encode($data)); 
            curl_setopt($curl, CURLOPT_HTTPHEADER, ['Content-Type: application/json']); 
        }

        $response = curl_exec($curl); 

        if ($response === false) {

            $error = curl_error($curl);
            curl_close($curl);
            throw new Exception("Erro na requisição: $error");
        }

        curl_close($curl); 

        return json_decode($response, true); 
    }

    public function loadGame() {
        return $this->callApi('loadGame', 'GET');
    }

    // Função para salvar o jogo (saveGame)
    public function saveGame() {
        return $this->callApi('saveGame', 'POST');
    }

}
