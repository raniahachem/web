<?php 

namespace App\Service;

class BadWordFilter
{
    private $badWords = ['fuck', 'ass', 'shit']; // Replace with your list of bad words

    public function filter(string $text): string
    {
        // Replace bad words with asterisks
        $filteredText = str_ireplace($this->badWords, '****', $text);

        return $filteredText;
    }
}