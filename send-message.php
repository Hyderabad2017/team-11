<?php
   
    require_once "vendor/autoload.php";
    use Twilio\Rest\Client;
    $AccountSid = "AC58806ed01d4dfbf3680d67c6703696a1";
    $AuthToken = "2470873709e3dada10574c0e4b5a80f6";

    $client = new Client($AccountSid, $AuthToken);
   
    
    $people = array(
       
        "+918886211191" => "Anu"

    );

    
    foreach ($people as $number => $name) {

        $sms = $client->account->messages->create(

           
            $number,

            array(
                
                'from' => "+18563145736 ", 
                
                'body' => "Hey $name, Monkey Party at 6PM. Bring Bananas!"
            )
        );

        echo "Sent message to $name";
    }
