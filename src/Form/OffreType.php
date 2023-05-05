<?php
namespace App\Form;

use App\Entity\Offre;
use App\Entity\Conducteur;
 
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;



class OffreType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('Destination')
            ->add('pt_depart')
            ->add('prix')
            // ->add('type_vehicule')
            ->add('type_vehicule', ChoiceType::class, [
                'choices'  => [
                    'Bus' => 'bus',
                    'Voiture' => 'voiture',
                    'Van' => 'van',
                ],
            ])
            ->add('id_conducteur', EntityType::class, [
                'class' => Conducteur::class,
                'choice_label' => 'id_conducteur', // Remplacez "nom_complet" par le nom de l'attribut qui représente le nom complet du conducteur dans l'entité Conducteur
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