<?php

namespace App\Service;

use App\Entity\Commentaire;
use Doctrine\Persistence\ManagerRegistry;
use Symfony\Component\HttpFoundation\RequestStack;

class CommentaireSearchService
{
    private $managerRegistry;
    private $requestStack;

    public function __construct(ManagerRegistry $managerRegistry, RequestStack $requestStack)
    {
        $this->managerRegistry = $managerRegistry;
        $this->requestStack = $requestStack;
    }

    public function searchCommentaires(): array
    {
        $request = $this->requestStack->getCurrentRequest();

        $repository = $this->managerRegistry->getRepository(Commentaire::class);
        $queryBuilder = $repository->createQueryBuilder('c');

        // Get the search term from the request
        $query = $request->query->get('query');

        // Build the query based on the search term
        if ($query) {
            $queryBuilder->andWhere('c.contenu LIKE :query')
                ->setParameter('query', '%' . $query . '%');
        }

        // Execute the query and retrieve the results
        $commentaires = $queryBuilder->getQuery()->getResult();

        // Return the results
        return $commentaires;
    }
}
