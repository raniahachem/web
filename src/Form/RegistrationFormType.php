<?php

namespace App\Form;

use App\Entity\Conducteur;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\IsTrue;
use Symfony\Component\Validator\Constraints\Length;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\IntegerType;
use Symfony\Component\Form\FormEvent;
use Symfony\Component\Form\FormEvents;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\EmailType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Validator\Constraints\NotBlank;

class RegistrationFormType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $license = ['A', 'B'];
    
        $towns = ['Tunis', 'Sfax', 'Sousse', 'Gabès', 'Nabeul', 'Bizerte', 'Kairouan', 'Gafsa', 'Monastir', 'Ariana', 'Mahdia', 'Sidi Bouzid', 'Ben Arous', 'Medenine', 'Manouba', 'Kasserine', 'Zaghouan', 'Kebili', 'Siliana', 'Tataouine', 'Kef', 'Jendouba', 'Tozeur'];
            
        $builder
            ->add('cin_conducteur', IntegerType::class)
            ->add('nom_conducteur', TextType::class)
            ->add('prenom_conducteur', TextType::class)
            ->add('telephone_conducteur', IntegerType::class)
            ->add('email_conducteur', EmailType::class)
            ->add('ville_conducteur', ChoiceType::class, [
                'choices' => array_combine($towns, $towns),
                'attr' => [
                    'class' => 'form-control',
                    'id' => 'ville_conducteur'
                ]
            ])
            ->add('type_de_permis', ChoiceType::class, [
                'choices' => array_combine($license, $license),
                'attr' => [
                    'class' => 'form-control',
                    'id' => 'type_de_permis'
                ]
            ])             
            ->add('image_conducteur', FileType::class, [
                'label' => 'Image',
                'required' => false,
                'attr' => [
                    'accept' => '',
                    'class' => 'app_update'
                ]
            ])
        
    
    
     
        

            ->add('agreeTerms', CheckboxType::class, [
                'mapped' => false,
                'constraints' => [
                    new IsTrue([
                        'message' => 'You should agree to our terms.',
                    ]),
                ],
            ])
            ->add('plainPassword', PasswordType::class, [
                // instead of being set onto the object directly,
                // this is read and encoded in the controller
                'mapped' => false,
                'attr' => ['autocomplete' => 'new-password'],
                'constraints' => [
                    new NotBlank([
                        'message' => 'Please enter a password',
                    ]),
                    new Length([
                        'min' => 6,
                        'minMessage' => 'Your password should be at least {{ limit }} characters',
                        // max length allowed by Symfony for security reasons
                        'max' => 4096,
                    ]),
                ],
            ]);
        
            $builder->addEventListener(FormEvents::POST_SUBMIT, function (FormEvent $event) {
                $form = $event->getForm();
                $data = $event->getData();
        
                $imageFile = $form->get('image_conducteur')->getData();
                if ($imageFile) {
                    $newFilename = 'img/' . uniqid() . '.' . $imageFile->guessExtension();
                    $data->setImageConducteur($newFilename);
                }
            });
            
    }
    





    
    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Conducteur::class,
        ]);
    }
    

}