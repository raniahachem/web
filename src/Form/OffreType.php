<?php

namespace App\Form;

use App\Entity\Offre;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use App\Entity\Conducteur;
use App\Entity\Vehicule;



use Symfony\Component\Form\Extension\Core\Type\ChoiceType;




class OffreType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        
             
            ->add('idConducteur')
            ->add('destination')
            ->add('ptDepart')
            ->add('prix')
            /*->add('typeVehicule', EntityType::class, [
                'class' => Vehicule::class,
                'placeholder' => 'Vehicule',
                'choice_label'=>'typeDuVehicule',
                'multiple' => false,
                'expanded' => false
            ])*/
            ->add('typeVehicule', ChoiceType::class, [
                'choices' => [
                    'Voiture' => 'voiture',
                    'Bus' => 'bus',
                    'Van' => 'van',
                ],
                'placeholder' => 'Choisir un type de véhicule',
                'required' => true,
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Offre::class,
        ]);
    }
}
