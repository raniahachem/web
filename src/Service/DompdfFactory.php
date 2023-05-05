<?php

namespace App\Service;


use Dompdf\Dompdf;
use Dompdf\Options;

class DompdfFactory
{
    public function create(): Dompdf
    {
        $options = new Options();
        $options->set('defaultFont', 'Arial');
        $options->set('isRemoteEnabled', true);

        $dompdf = new Dompdf($options);

        return $dompdf;
    }
}
