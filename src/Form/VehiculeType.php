<?php

namespace App\Form;

use App\Entity\Vehicule;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use App\Entity\Conducteur;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;


class VehiculeType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('id')
            ->add('type_dy_vehicule', ChoiceType::class, [
                'choices' => [
                    'Voiture' => 'voiture',
                    'Van' => 'van',
                    'Camion' => 'camion',
                    'Bus' => 'bus',
                ],
                'label' => 'Type du Vehicule',
            ])
            ->add('marque', ChoiceType::class, [
                'choices' => array_flip($options['marque_choices']),
                'label' => 'Marque',
            ])
            ->add('etat')
            ->add('kilometrage')
            ->add('imagev', FileType::class, [
                'label' => 'Image',
                'attr' => ['class' => 'form-control'],
                'required' => false,
                
            ])
            ->add('submit', SubmitType::class, [
                'label' => 'Save',
            ]);
            
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Vehicule::class,
            'marque_choices' => [],
        ]);
    }

    
}
