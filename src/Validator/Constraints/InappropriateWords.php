<?php
// src/Validator/Constraints/InappropriateWords.php

namespace App\Validator\Constraints;

use Symfony\Component\Validator\Constraint;

/**
 * @Annotation
 */
class InappropriateWords extends Constraint
{
    public $message = 'The description contains inappropriate words: {{ words }}';
}
