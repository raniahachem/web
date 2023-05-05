<?php
namespace App\Validator;

use Symfony\Component\Validator\Constraint;

/**
 * @Annotation
 */
class TypeDuVehicule extends Constraint
{
    public $message = 'The type "{{ value }}" is not a valid vehicle type. Allowed types are: {{ allowed }}.';
    public $allowedValues = ['Voiture', 'Van', 'Camion', 'Bus'];

    public function getRequiredOptions()
    {
        return ['allowedValues'];
    }
}
