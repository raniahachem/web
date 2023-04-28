<?php

namespace App\Repository;

use Symfony\Component\HttpFoundation\RequestStack;
use App\Entity\Offre;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

use Symfony\Component\Mime\Email;
 
use Symfony\Component\Mailer\MailerInterface;
use App\Service\MailerService;

/**
 * @extends ServiceEntityRepository<Offre>
 *
 * @method Offre|null find($id, $lockMode = null, $lockVersion = null)
 * @method Offre|null findOneBy(array $criteria, array $orderBy = null)
 * @method Offre[]    findAll()
 * @method Offre[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class OffreRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry, RequestStack $requestStack)
    {
        parent::__construct($registry, Offre::class);
        $this->requestStack = $requestStack;
    }

    public function save(Offre $entity,MailerInterface $mailer, bool $flush = false): void
    {
        $this->getEntityManager()->persist($entity);


        $email = (new Email())
        ->from('nour.benmiled@esprit.tn')
        ->to('ons.hamdi@esprit.tn')
        ->subject('bienvenue dans notre espace client!')
        ->html('<p>  avec succes </p>');

    $mailer->send($email);
    
    $session = $this->requestStack->getCurrentRequest()->getSession();
    $session->getFlashBag()->add(
        'success',
        '  avec succès'
       
    );


        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

    public function remove(Offre $entity, bool $flush = false): void
    {
        $this->getEntityManager()->remove($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

 public function removeByRe(int $id, bool $flush = false): void
{
    $offre = $this->getEntityManager()->getRepository(Offre::class)->find($id);

    if ($offre !== null) {
        $this->getEntityManager()->remove($offre);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }
}
/**
 * @return Offre|null Returns an Offre object or null if not found
 */
public function findById(int $id): ?Offre
{
    return $this->createQueryBuilder('o')
        ->andWhere('o.idOffre = :id')
        ->setParameter('id', $id)
        ->getQuery()
        ->getOneOrNullResult();
}


//    /**
//     * @return Offre[] Returns an array of Offre objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('o')
//            ->andWhere('o.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('o.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Offre
//    {
//        return $this->createQueryBuilder('o')
//            ->andWhere('o.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
